package io.github.rxcats.aesrestapidemo.entity;

import java.time.Instant;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class Book {

    String isbn;

    String name;

    String author;

    Instant createdAt;

}
