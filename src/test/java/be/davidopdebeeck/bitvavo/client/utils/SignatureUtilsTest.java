package be.davidopdebeeck.bitvavo.client.utils;

import org.junit.jupiter.api.Test;

import static be.davidopdebeeck.bitvavo.client.utils.SignatureUtils.createSignature;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SignatureUtilsTest {

    @Test
    void test_createSignature() {
        String body = "{\"market\":\"BTC-EUR\",\"side\":\"buy\",\"price\":\"5000\",\"amount\":\"1.23\",\"orderType\":\"limit\"}";
        String signature = createSignature("/v2/order", "POST", body, 1548172481125L, "bitvavo");

        assertEquals("44d022723a20973a18f7ee97398b9fdd405d2d019c8d39e24b8cc0dcb39ca016", signature);
    }
}