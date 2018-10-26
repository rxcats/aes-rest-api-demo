package io.github.rxcats.aesrestapidemo.message;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import io.github.rxcats.aesrestapidemo.entity.Book;
import io.github.rxcats.aesrestapidemo.mvc.converter.AesApiBodyType;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class BookRequest implements AesApiBodyType {

    Book book;

}
