package com.github.ratel.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public class SaltProvider {

    private static final String SECRET_KEY = "the_most_salty_key99";

    public static String getRandomSalt() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[20];
        random.nextBytes(bytes);
        Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
        String salt = encoder.encodeToString(bytes);
        return salt;
    }

    public static String encrypt(String data) {
        byte[] decodedKey = Base64.getMimeDecoder().decode(SECRET_KEY);
        try {
            Cipher cipher = Cipher.getInstance("AES");
            SecretKey originalKey = new SecretKeySpec(Arrays.copyOf(decodedKey, 16), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, originalKey);
            byte[] cipherText = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(cipherText);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Error occurred while encrypting data", e);
        }
    }

    public static String decrypt(String encryptedString) {
        byte[] decodedKey = Base64.getMimeDecoder().decode(SECRET_KEY);
        try {
            Cipher cipher = Cipher.getInstance("AES");
            SecretKey originalKey = new SecretKeySpec(Arrays.copyOf(decodedKey, 16), "AES");
            cipher.init(Cipher.DECRYPT_MODE, originalKey);
            byte[] cipherText = cipher.doFinal(Base64.getDecoder().decode(encryptedString));
            return new String(cipherText);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Error occurred while decrypting data", e);
        }
    }
}
