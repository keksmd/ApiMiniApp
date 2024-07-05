package org.alexkekiy.util;

import java.util.Base64;

public class Base64Encrypter {
    private Base64Encrypter() {
        throw new AssertionError();
    }

    public static String encodeEmailAndCode(String email, String code) {
        return Base64.getEncoder().encodeToString((email + ":" + code).getBytes());
    }
}
