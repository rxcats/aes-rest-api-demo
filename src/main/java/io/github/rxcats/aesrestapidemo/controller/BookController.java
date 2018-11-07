package io.github.rxcats.aesrestapidemo.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.rxcats.aesrestapidemo.config.Constants;
import io.github.rxcats.aesrestapidemo.message.AesResponseEntity;
import io.github.rxcats.aesrestapidemo.message.BookRequest;
import io.github.rxcats.aesrestapidemo.mvc.annotation.EnableAesErrorHandler;

@EnableAesErrorHandler
@RequestMapping(value = "/aes",
    produces = {Constants.API_MEDIA_TYPE_ENC_VALUE, Constants.API_MEDIA_TYPE_JSON_VALUE},
    consumes = {Constants.API_MEDIA_TYPE_ENC_VALUE, Constants.API_MEDIA_TYPE_JSON_VALUE})
@RestController
public class BookController {

    @PostMapping(value = "/book")
    public AesResponseEntity book(@Valid @RequestBody BookRequest request) {
        return AesResponseEntity.of(request);
    }

    @PostMapping(value = "/book/empty")
    public AesResponseEntity book() {
        return AesResponseEntity.of();
    }

    @PostMapping(value = "/book/error")
    public AesResponseEntity error() {
        throw new RuntimeException("just error");
    }

}
