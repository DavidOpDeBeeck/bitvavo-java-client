package be.davidopdebeeck.bitvavo.client.ratelimiter;

import be.davidopdebeeck.bitvavo.client.response.BitvavoErrorMessage;
import be.davidopdebeeck.bitvavo.client.response.BitvavoResponse;
import be.davidopdebeeck.bitvavo.client.response.BitvavoResponseMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static be.davidopdebeeck.bitvavo.client.utils.ThreadUtils.waitForMillis;
import static java.lang.Long.parseLong;
import static java.time.Instant.ofEpochMilli;
import static java.util.regex.Pattern.compile;
import static org.apache.commons.lang.ObjectUtils.compare;

public class BitvavoServerSideRateLimiter implements BitvavoRateLimiter {

    private static final Logger LOGGER = LoggerFactory.getLogger(BitvavoServerSideRateLimiter.class);
    private static final Pattern RATE_LIMIT_ERROR_MESSAGE_PATTERN = compile("Your IP or API key has been banned for " +
        "not respecting the rate limit. The ban expires at ([0-9]*).");

    private Instant resetAt;
    private long weightRemaining;

    public BitvavoServerSideRateLimiter() {
        this.resetAt = null;
        this.weightRemaining = 0;
    }

    @Override
    public void verifyActionCanBeExecuted(long actionWeight) {
        if (compare(Instant.now(), resetAt) >= 0 || actionWeight <= weightRemaining) {
            return;
        }

        long waitTimeInMillis = calculateWaitTimeInMillis();
        LOGGER.warn("Rate limit reached! Current remaining weight {}, weight needed {}. " +
            "Waiting {} milliseconds for rate limit to reset at {}.", weightRemaining, actionWeight, waitTimeInMillis, resetAt);
        waitForMillis(waitTimeInMillis);

        verifyActionCanBeExecuted(actionWeight);
    }

    @Override
    public void handleResponse(BitvavoResponse<?> response, BitvavoResponseMetadata metadata) {
        metadata.findValues("Bitvavo-Ratelimit-ResetAt", String.class)
            .map(values -> parseLong(values.getFirst()))
            .ifPresent(this::updateResetAt);
        metadata.findValues("Bitvavo-Ratelimit-Remaining", String.class)
            .map(values -> parseLong(values.getFirst()))
            .ifPresent(this::updateWeightRemaining);

        switch (response) {
            case BitvavoResponse.Ok(var ignored) -> {}
            case BitvavoResponse.Error(var errorMessage) -> handlePossibleRateLimitError(errorMessage);
        }
    }

    private void handlePossibleRateLimitError(BitvavoErrorMessage errorMessage) {
        if (errorMessage.isRateLimitRelated()) {
            Matcher matcher = RATE_LIMIT_ERROR_MESSAGE_PATTERN.matcher(errorMessage.getErrorMessage());
            if (matcher.find()) {
                weightRemaining = 0;
                resetAt = Instant.ofEpochMilli(parseLong(matcher.group(1)));
                LOGGER.warn("Server-side rate limit reached! The rate limit will reset at {}.", resetAt);
            }
        }
    }

    private void updateWeightRemaining(long weightRemaining) {
        LOGGER.debug("Updated remaining weight to {}", weightRemaining);
        this.weightRemaining = weightRemaining;
    }

    private void updateResetAt(long resetAtInMillis) {
        this.resetAt = ofEpochMilli(resetAtInMillis);
    }

    private long calculateWaitTimeInMillis() {
        long currentTimeMillis = Instant.now().toEpochMilli();
        long resetAtMillis = resetAt.toEpochMilli();
        return Math.max(0, resetAtMillis - currentTimeMillis);
    }
}
