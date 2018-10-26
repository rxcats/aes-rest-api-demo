package io.github.rxcats.aesrestapidemo.mvc.converter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import io.github.rxcats.aesrestapidemo.config.Constants;

@Slf4j
public class AesApiHttpMessageConverter extends AbstractHttpMessageConverter<AesApiBodyType> {

    private ObjectMapper objectMapper;

    private boolean aesEncrypt = false;

    public AesApiHttpMessageConverter(ObjectMapper objectMapper) {
        super(StandardCharsets.UTF_8, Constants.API_MEDIA_TYPE);
        this.objectMapper = objectMapper;
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return List.of(clazz.getInterfaces()).contains(AesApiBodyType.class);
    }

    private boolean startsWithJsonString(String content) {
        return content.startsWith("{") || content.startsWith("[");
    }

    @Override
    protected AesApiBodyType readInternal(Class<? extends AesApiBodyType> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {

        String content = IOUtils.toString(inputMessage.getBody(), super.getDefaultCharset());

        aesEncrypt = false;
        if (!startsWithJsonString(content)) {
            content = AES256Cipher.decode(content);
            aesEncrypt = true;
        }

        if (log.isDebugEnabled()) {
            log.debug("request body ### : {}", content);
        }

        return objectMapper.readValue(content, clazz);
    }

    @Override
    protected void writeInternal(AesApiBodyType object, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

        String content = objectMapper.writeValueAsString(object);

        if (log.isDebugEnabled()) {
            log.debug("response body ### : {}", content);
        }

        if (aesEncrypt) {
            content = AES256Cipher.encode(content);
        }

        try {
            outputMessage.getBody().write(content.getBytes());
        } finally {
            outputMessage.getBody().close();
        }
    }

}
