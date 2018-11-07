package io.github.rxcats.aesrestapidemo.config;

import org.springframework.http.MediaType;

public class Constants {
    public static final String API_MEDIA_TYPE_ENC_VALUE = "application/vnd.rxcats.enc";
    public static final MediaType API_MEDIA_TYPE_ENC = MediaType.valueOf(API_MEDIA_TYPE_ENC_VALUE);
    public static final MediaType API_MEDIA_TYPE_ENC_UTF8 = MediaType.valueOf(API_MEDIA_TYPE_ENC_VALUE + ";charset=UTF-8");

    public static final String API_MEDIA_TYPE_JSON_VALUE = "application/vnd.rxcats.json";
    public static final MediaType API_MEDIA_TYPE_JSON = MediaType.valueOf(API_MEDIA_TYPE_JSON_VALUE);
    public static final MediaType API_MEDIA_TYPE_JSON_UTF8 = MediaType.valueOf(API_MEDIA_TYPE_JSON_VALUE + ";charset=UTF-8");

    public static final String AES_KEY = "9971e50fb9064cf59fdeb17b56d0b221";

    private Constants() {

    }
}
