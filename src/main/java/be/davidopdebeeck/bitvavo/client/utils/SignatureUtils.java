package be.davidopdebeeck.bitvavo.client.utils;

import static be.davidopdebeeck.bitvavo.client.utils.HMAC.sha256;

public class SignatureUtils {

    public static String createSignature(String path, String method, long currentTimeMillis, String apiSecret) {
        String message = currentTimeMillis + method + path;
        System.out.println("Creating signature " + message);
        return sha256(apiSecret, message);
    }
}
