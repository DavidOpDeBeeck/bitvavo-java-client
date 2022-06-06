package be.davidopdebeeck.bitvavo.client.ratelimiter;

import be.davidopdebeeck.bitvavo.client.response.BitvavoErrorMessage;
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

public class BitvavoRateLimiter {

    private static final Logger LOGGER = LoggerFactory.getLogger(BitvavoRateLimiter.class);
    private static final Pattern RATE_LIMIT_ERROR_MESSAGE_PATTERN = compile("Your IP or API key has been banned for " +
        "not respecting the rate limit. The ban expires at ([0-9]*).");

    private Instant resetAt;
    private long weightRemaining;

    public BitvavoRateLimiter() {
        this.resetAt = null;
        this.weightRemaining = 0;
    }

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

    public void handlePossibleRateLimitError(BitvavoErrorMessage errorMessage) {
        if (errorMessage.isRateLimitRelated()) {
            Matcher matcher = RATE_LIMIT_ERROR_MESSAGE_PATTERN.matcher(errorMessage.getErrorMessage());
            if (matcher.find()) {
                weightRemaining = 0;
                resetAt = Instant.ofEpochMilli(parseLong(matcher.group(1)));
                LOGGER.warn("Server-side rate limit reached! The rate limit will reset at {}.", resetAt);
            }
        }
    }

    public void updateWeightRemaining(long weightRemaining) {
        LOGGER.debug("Updated remaining weight to {}", weightRemaining);
        this.weightRemaining = weightRemaining;
    }

    public void updateResetAt(long resetAtInMillis) {
        this.resetAt = ofEpochMilli(resetAtInMillis);
    }

    private long calculateWaitTimeInMillis() {
        long currentTimeMillis = Instant.now().toEpochMilli();
        long resetAtMillis = resetAt.toEpochMilli();
        return Math.max(0, resetAtMillis - currentTimeMillis);
    }
}
