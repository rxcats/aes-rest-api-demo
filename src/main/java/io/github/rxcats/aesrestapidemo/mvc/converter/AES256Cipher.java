package io.github.rxcats.aesrestapidemo.mvc.converter;

import java.nio.charset.StandardCharsets;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;

import io.github.rxcats.aesrestapidemo.config.Constants;

public class AES256Cipher {

    private static final byte[] IV_BYTES = {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";

    private AES256Cipher() {

    }

    public static String encode(String str) {
        try {
            byte[] textBytes = str.getBytes(StandardCharsets.UTF_8);
            AlgorithmParameterSpec ivSpec = new IvParameterSpec(IV_BYTES);
            SecretKeySpec newKey = new SecretKeySpec(Constants.AES_KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, newKey, ivSpec);
            return Base64.encodeBase64String(cipher.doFinal(textBytes));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public static String decode(String str) {
        try {
            byte[] textBytes = Base64.decodeBase64(str);
            AlgorithmParameterSpec ivSpec = new IvParameterSpec(IV_BYTES);
            SecretKeySpec newKey = new SecretKeySpec(Constants.AES_KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, newKey, ivSpec);
            return new String(cipher.doFinal(textBytes), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}