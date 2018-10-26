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
@RequestMapping(produces = Constants.API_MEDIA_TYPE_VALUE, consumes = Constants.API_MEDIA_TYPE_VALUE)
@RestController
public class BookController {

    @PostMapping("/book")
    public AesResponseEntity book(@Valid @RequestBody BookRequest request) {
        return AesResponseEntity.of(request);
    }

    @PostMapping("/book/error")
    public AesResponseEntity error() {
        throw new RuntimeException("just error");
    }

}
