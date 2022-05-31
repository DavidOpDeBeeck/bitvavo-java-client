package be.davidopdebeeck.bitvavo.client.api.market.trades;

import java.time.Instant;
import java.util.Optional;

import static java.util.Optional.ofNullable;

public class BitvavoMarketTradesRequest {

    private final Integer limit;
    private final Instant start;
    private final Instant end;
    private final String tradeIdFrom;
    private final String tradeIdTo;

    private BitvavoMarketTradesRequest(Builder builder) {
        limit = builder.limit;
        start = builder.start;
        end = builder.end;
        tradeIdFrom = builder.tradeIdFrom;
        tradeIdTo = builder.tradeIdTo;
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

        private Integer limit;
        private Instant start;
        private Instant end;
        private String tradeIdFrom;
        private String tradeIdTo;

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

        public BitvavoMarketTradesRequest build() {
            return new BitvavoMarketTradesRequest(this);
        }
    }
}
