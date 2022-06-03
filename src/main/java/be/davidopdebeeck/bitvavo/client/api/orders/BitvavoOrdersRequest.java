package be.davidopdebeeck.bitvavo.client.api.orders;

import be.davidopdebeeck.bitvavo.client.api.market.BitvavoMarket;

import java.time.Instant;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

public class BitvavoOrdersRequest {

    private final BitvavoMarket market;
    private final Integer limit;
    private final Instant start;
    private final Instant end;
    private final String orderIdFrom;
    private final String orderIdTo;

    private BitvavoOrdersRequest(Builder builder) {
        market = requireNonNull(builder.market);
        limit = builder.limit;
        start = builder.start;
        end = builder.end;
        orderIdFrom = builder.orderIdFrom;
        orderIdTo = builder.orderIdTo;
    }

    public BitvavoMarket getMarket() {
        return market;
    }

    public Optional<Integer> getLimit() {
        return Optional.ofNullable(limit);
    }

    public Optional<Instant> getStart() {
        return Optional.ofNullable(start);
    }

    public Optional<Instant> getEnd() {
        return Optional.ofNullable(end);
    }

    public Optional<String> getOrderIdFrom() {
        return Optional.ofNullable(orderIdFrom);
    }

    public Optional<String> getOrderIdTo() {
        return Optional.ofNullable(orderIdTo);
    }

    public static final class Builder {
        private BitvavoMarket market;
        private Integer limit;
        private Instant start;
        private Instant end;
        private String orderIdFrom;
        private String orderIdTo;

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

        public Builder withOrderIdFrom(String orderIdFrom) {
            this.orderIdFrom = orderIdFrom;
            return this;
        }

        public Builder withOrderIdTo(String orderIdTo) {
            this.orderIdTo = orderIdTo;
            return this;
        }

        public BitvavoOrdersRequest build() {
            return new BitvavoOrdersRequest(this);
        }
    }
}
