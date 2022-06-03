package be.davidopdebeeck.bitvavo.client.api.order;

import be.davidopdebeeck.bitvavo.client.api.market.BitvavoMarket;

import static java.util.Objects.requireNonNull;

public class BitvavoOrderRequest {

    private final BitvavoMarket market;
    private final String orderId;

    private BitvavoOrderRequest(Builder builder) {
        market = requireNonNull(builder.market);
        orderId = requireNonNull(builder.orderId);
    }

    public BitvavoMarket getMarket() {
        return market;
    }

    public String getOrderId() {
        return orderId;
    }

    public static final class Builder {

        private BitvavoMarket market;
        private String orderId;

        public Builder withMarket(BitvavoMarket market) {
            this.market = market;
            return this;
        }

        public Builder withOrderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public BitvavoOrderRequest build() {
            return new BitvavoOrderRequest(this);
        }
    }
}
