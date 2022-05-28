package be.davidopdebeeck.bitvavo.client.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static be.davidopdebeeck.bitvavo.client.utils.HMAC.sha256;

public class SignatureUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(SignatureUtils.class);

    public static String createSignature(String path, String method, long currentTimeMillis, String apiSecret) {
        String message = currentTimeMillis + method + path;
        String signature = sha256(apiSecret, message);
        LOGGER.debug("Created signature '{}' for message '{}'", signature, message);
        return signature;
    }
}
