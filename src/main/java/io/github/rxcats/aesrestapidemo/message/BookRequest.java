package io.github.rxcats.aesrestapidemo.message;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import io.github.rxcats.aesrestapidemo.entity.Book;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class BookRequest {

    Book book;

}
