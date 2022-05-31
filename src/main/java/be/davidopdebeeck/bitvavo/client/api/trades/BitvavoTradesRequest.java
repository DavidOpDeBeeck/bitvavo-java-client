package be.davidopdebeeck.bitvavo.client.api.trades;

import be.davidopdebeeck.bitvavo.client.api.market.BitvavoMarket;

import java.time.Instant;
import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;

public class BitvavoTradesRequest {

    private final BitvavoMarket market;
    private final Integer limit;
    private final Instant start;
    private final Instant end;
    private final String tradeIdFrom;
    private final String tradeIdTo;

    private BitvavoTradesRequest(Builder builder) {
        market = requireNonNull(builder.market);
        limit = builder.limit;
        start = builder.start;
        end = builder.end;
        tradeIdFrom = builder.tradeIdFrom;
        tradeIdTo = builder.tradeIdTo;
    }

    public BitvavoMarket getMarket() {
        return market;
    }

    public Optional<Integer> getLimit() {
        return ofNullable(limit);
    }

    public Optional<Instant> getStart() {
        return ofNullable(start);
    }

    public Optional<Instant> getEnd() {
        return ofNullable(end);
    }

    public Optional<String> getTradeIdFrom() {
        return ofNullable(tradeIdFrom);
    }

    public Optional<String> getTradeIdTo() {
        return ofNullable(tradeIdTo);
    }

    public static final class Builder {

        private BitvavoMarket market;
        private Integer limit;
        private Instant start;
        private Instant end;
        private String tradeIdFrom;
        private String tradeIdTo;

        public Builder withMarket(BitvavoMarket market) {
            this.market = market;
            return this;
        }

        public Builder withLimit(Integer limit) {
            this.limit = limit;
            return this;
        }

        public Builder withStart(Instant start) {
            this.start = start;
            return this;
        }

        public Builder withEnd(Instant end) {
            this.end = end;
            return this;
        }

        public Builder withTradeIdFrom(String tradeIdFrom) {
            this.tradeIdFrom = tradeIdFrom;
            return this;
        }

        public Builder withTradeIdTo(String tradeIdTo) {
            this.tradeIdTo = tradeIdTo;
            return this;
        }

        public BitvavoTradesRequest build() {
            return new BitvavoTradesRequest(this);
        }
    }
}
