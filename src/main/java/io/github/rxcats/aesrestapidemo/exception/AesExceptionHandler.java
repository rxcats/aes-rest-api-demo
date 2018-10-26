package io.github.rxcats.aesrestapidemo.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

import io.github.rxcats.aesrestapidemo.message.AesResponseEntity;
import io.github.rxcats.aesrestapidemo.mvc.annotation.EnableAesErrorHandler;

@Slf4j
@RestControllerAdvice(annotations = {EnableAesErrorHandler.class})
public class AesExceptionHandler {

    @ResponseStatus
    @ExceptionHandler({Exception.class})
    public AesResponseEntity handleException(Exception e) {
        log.error(e.getMessage(), e);
        return AesResponseEntity.of(-1, null, e.getMessage());
    }

}
