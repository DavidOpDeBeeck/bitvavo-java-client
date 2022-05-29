package be.davidopdebeeck.bitvavo.client.api.market.candles;

import be.davidopdebeeck.bitvavo.client.api.interval.BitvavoInterval;

import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;

public class BitvavoMarketCandlesRequest {

    private final BitvavoInterval interval;
    private final Integer limit;
    private final Long start;
    private final Long end;

    private BitvavoMarketCandlesRequest(Builder builder) {
        interval = requireNonNull(builder.interval);
        limit = builder.limit;
        start = builder.start;
        end = builder.end;
    }

    public BitvavoInterval getInterval() {
        return interval;
    }

    public Optional<Integer> getLimit() {
        return ofNullable(limit);
    }

    public Optional<Long> getStart() {
        return ofNullable(start);
    }

    public Optional<Long> getEnd() {
        return ofNullable(end);
    }

    public static final class Builder {

        private BitvavoInterval interval;
        private Integer limit;
        private Long start;
        private Long end;

        public Builder withInterval(BitvavoInterval interval) {
            this.interval = interval;
            return this;
        }

        public Builder withLimit(Integer limit) {
            this.limit = limit;
            return this;
        }

        public Builder withStart(Long start) {
            this.start = start;
            return this;
        }

        public Builder withEnd(Long end) {
            this.end = end;
            return this;
        }

        public BitvavoMarketCandlesRequest build() {
            return new BitvavoMarketCandlesRequest(this);
        }
    }
}
