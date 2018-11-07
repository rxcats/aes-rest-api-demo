package io.github.rxcats.aesrestapidemo;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

import io.github.rxcats.aesrestapidemo.mvc.converter.AES256Cipher;

@Slf4j
public class AesEncryptTest {

    @Test
    public void test() {
        String json = "{\"book\":{\"isbn\":\"9780007525546\",\"name\":\"반지의 제왕\",\"author\":\"J. R. R. 톨킨\",\"createdAt\":\"2018-10-26T08:05:57+00:00\"}}";
        String req = AES256Cipher.encode(json);
        log.info("encrypted : {}", req);

        String enc = "ho+Ey3tpd+P1OGKzX3L9dRexRS0C2PSEVe5Htv7x222+70MuzPmeLo5Dm+oPpcZuAS6932+mHefs1NjTwLcfvr0PPdZ5BkL48fnhpaNrwuRaNDG7BdS3MngNxm/XZREhKZ1REjYmXXmG3EO6SJz/dGU0duEmaBx5rydVxuJoCjud0l1tlB8e1yoYpJe6TgYOM5lEnGMvGrP8C6863j6xTUZzYI6m4uIzjp1aN2SBtuU4nRXWGT74Y5yRe+zVQzWu";
        log.info("res : {}", AES256Cipher.decode(enc));
    }

}
