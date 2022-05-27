package be.davidopdebeeck.bitvavo.client.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.commons.codec.binary.Hex.encodeHex;

public class HMAC {

    private static final String SHA_256_ALGORITHM = "HmacSHA256";

    public static String sha256(String secret, String message) {
        try {
            Mac mac = Mac.getInstance(SHA_256_ALGORITHM);
            mac.init(createSecretKeySpec(secret));
            byte[] encryptedMessage = mac.doFinal(message.getBytes(UTF_8));

            return new String(encodeHex(encryptedMessage));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static SecretKeySpec createSecretKeySpec(String secret) {
        return new SecretKeySpec(secret.getBytes(UTF_8), SHA_256_ALGORITHM);
    }
}
