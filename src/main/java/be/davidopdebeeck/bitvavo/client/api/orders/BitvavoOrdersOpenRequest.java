package be.davidopdebeeck.bitvavo.client.api.orders;

import be.davidopdebeeck.bitvavo.client.api.market.BitvavoMarket;

import static java.util.Objects.requireNonNull;

public class BitvavoOrdersOpenRequest {

    private final BitvavoMarket market;

    private BitvavoOrdersOpenRequest(Builder builder) {
        market = requireNonNull(builder.market);
    }

    public BitvavoMarket getMarket() {
        return market;
    }

    public static final class Builder {

        private BitvavoMarket market;

        public Builder withMarket(BitvavoMarket market) {
            this.market = market;
            return this;
        }

        public BitvavoOrdersOpenRequest build() {
            return new BitvavoOrdersOpenRequest(this);
        }
    }
}
