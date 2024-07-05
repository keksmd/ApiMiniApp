package org.alexkekiy;

import org.alexkekiy.util.Base64Encrypter;

public class Base64EncrypterTest {

    public static void main(String[] args) {
        String email = "lexagri200430@gmail.com";
        String code = "68953e8c27925818df633ea930928e81";
       assert Base64Encrypter.encodeEmailAndCode(email,code).equals("bGV4YWdyaTIwMDQzMEBnbWFpbC5jb206Njg5NTNlOGMyNzkyNTgxOGRmNjMzZWE5MzA5MjhlODE=");



    }

}