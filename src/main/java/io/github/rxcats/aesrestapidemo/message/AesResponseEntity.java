package io.github.rxcats.aesrestapidemo.message;

import lombok.Data;

@Data
public class AesResponseEntity<T> {

    private int code = 0;

    private T result;

    private String message = "";

    private long timestamp = System.currentTimeMillis();

    private AesResponseEntity() {

    }

    private AesResponseEntity(T result) {
        this();
        this.result = result;
    }

    private AesResponseEntity(int code, T result) {
        this(result);
        this.code = code;
    }

    private AesResponseEntity(int code, T result, String message) {
        this(result);
        this.code = code;
        this.message = message;
    }

    public static <T> AesResponseEntity<T> of() {
        return new AesResponseEntity<>();
    }

    public static <T> AesResponseEntity<T> of(T result) {
        return new AesResponseEntity<>(result);
    }

    public static <T> AesResponseEntity<T> of(int code, T result) {
        return new AesResponseEntity<>(code, result);
    }

    public static <T> AesResponseEntity<T> of(int code, T result, String message) {
        return new AesResponseEntity<>(code, result, message);
    }

}
