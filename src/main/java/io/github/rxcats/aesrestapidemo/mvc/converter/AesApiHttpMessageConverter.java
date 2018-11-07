package io.github.rxcats.aesrestapidemo.mvc.converter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import io.github.rxcats.aesrestapidemo.config.Constants;
import io.github.rxcats.aesrestapidemo.exception.AesDecodeException;
import io.github.rxcats.aesrestapidemo.exception.AesEncodeException;

@Slf4j
public class AesApiHttpMessageConverter extends AbstractHttpMessageConverter<Object> {

    private ObjectMapper objectMapper;

    public AesApiHttpMessageConverter(ObjectMapper objectMapper) {
        super(StandardCharsets.UTF_8, Constants.API_MEDIA_TYPE_ENC, Constants.API_MEDIA_TYPE_JSON);
        this.objectMapper = objectMapper;
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return super.getSupportedMediaTypes().contains(Constants.API_MEDIA_TYPE_ENC) ||
            super.getSupportedMediaTypes().contains(Constants.API_MEDIA_TYPE_JSON);
    }

    private boolean isAesEncrypt(@Nullable MediaType contentType) throws IOException {
        return contentType != null &&
            (contentType.includes(Constants.API_MEDIA_TYPE_ENC) || contentType.includes(Constants.API_MEDIA_TYPE_ENC_UTF8));
    }

    @Override
    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {

        String content = IOUtils.toString(inputMessage.getBody(), super.getDefaultCharset());

        if (isAesEncrypt(inputMessage.getHeaders().getContentType())) {
            try {
                content = AES256Cipher.decode(content);
            } catch (AesDecodeException e) {
                throw new RuntimeException("AES decode failure");
            }
        }

        if (log.isDebugEnabled()) {
            log.debug("request body ### : {}", content);
        }

        return objectMapper.readValue(content, clazz);
    }

    @Override
    protected void writeInternal(Object object, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

        String content = objectMapper.writeValueAsString(object);

        if (log.isDebugEnabled()) {
            log.debug("response body ### : {}", content);
        }

        if (isAesEncrypt(outputMessage.getHeaders().getContentType())) {
            try {
                content = AES256Cipher.encode(content);
            } catch (AesEncodeException e) {
                throw new RuntimeException("AES encode failure");
            }
        }

        try {
            outputMessage.getBody().write(content.getBytes());
        } finally {
            outputMessage.getBody().close();
        }
    }

}
