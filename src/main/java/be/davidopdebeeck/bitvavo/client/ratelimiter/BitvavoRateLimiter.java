package be.davidopdebeeck.bitvavo.client.ratelimiter;

import be.davidopdebeeck.bitvavo.client.response.BitvavoResponse;
import be.davidopdebeeck.bitvavo.client.response.BitvavoResponseMetadata;

public interface BitvavoRateLimiter {

    void verifyActionCanBeExecuted(long actionWeight);

    void handleResponse(BitvavoResponse<?> response, BitvavoResponseMetadata metadata);
}
