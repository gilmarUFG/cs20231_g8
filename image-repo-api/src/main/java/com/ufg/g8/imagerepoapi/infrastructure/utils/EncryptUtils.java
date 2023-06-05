package com.ufg.g8.imagerepoapi.infrastructure.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptUtils {

    private static final int STRENGTH = 12;

    public static String encode(String rawText) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(STRENGTH);
        return encoder.encode(rawText);
    }

    public static boolean matchTextWithEncoded(String rawText, String encoded) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(STRENGTH);
        return encoder.matches(rawText, encoded);
    }

}
