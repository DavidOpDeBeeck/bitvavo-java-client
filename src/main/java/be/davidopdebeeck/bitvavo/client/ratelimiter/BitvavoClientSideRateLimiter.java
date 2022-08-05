package be.davidopdebeeck.bitvavo.client.ratelimiter;

import be.davidopdebeeck.bitvavo.client.response.BitvavoResponse;
import be.davidopdebeeck.bitvavo.client.response.BitvavoResponseMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

import static be.davidopdebeeck.bitvavo.client.utils.ThreadUtils.waitForMillis;
import static java.util.Objects.requireNonNull;

public class BitvavoClientSideRateLimiter implements BitvavoRateLimiter {

    private static final Logger LOGGER = LoggerFactory.getLogger(BitvavoClientSideRateLimiter.class);

    private final long period;
    private final TimeUnit interval;
    private final long weight;

    private long weightRemaining;

    private BitvavoClientSideRateLimiter(Builder builder) {
        this.period = requireNonNull(builder.period);
        this.interval = requireNonNull(builder.interval);
        this.weight = requireNonNull(builder.weight);
        this.weightRemaining = weight;
    }

    @Override
    public void verifyActionCanBeExecuted(long actionWeight) {
        if (actionWeight <= weightRemaining) {
            this.weightRemaining -= actionWeight;
            return;
        }

        long currentTimeMillis = Instant.now().toEpochMilli();
        long waitTimeInMillis = interval.toMillis(period);
        Instant resetAt = Instant.ofEpochMilli(currentTimeMillis + waitTimeInMillis);

        LOGGER.warn("Rate limit reached! Current remaining weight {}, weight needed {}. " +
            "Waiting {} milliseconds for rate limit to reset at {}.", weightRemaining, actionWeight, waitTimeInMillis, resetAt);
        waitForMillis(waitTimeInMillis);

        this.weightRemaining = weight;

        verifyActionCanBeExecuted(actionWeight);
    }

    @Override
    public void handleResponse(BitvavoResponse<?> response, BitvavoResponseMetadata metadata) {

    }

    public static final class Builder {

        private Long period;
        private TimeUnit interval;
        private Long weight;

        public Builder withPeriod(long period) {
            this.period = period;
            return this;
        }

        public Builder withInterval(TimeUnit interval) {
            this.interval = interval;
            return this;
        }

        public Builder withWeight(long weight) {
            this.weight = weight;
            return this;
        }

        public BitvavoClientSideRateLimiter build() {
            return new BitvavoClientSideRateLimiter(this);
        }
    }
}
