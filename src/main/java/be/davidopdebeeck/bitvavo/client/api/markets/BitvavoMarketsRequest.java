package be.davidopdebeeck.bitvavo.client.api.markets;

import be.davidopdebeeck.bitvavo.client.api.market.BitvavoMarket;

import static java.util.Objects.requireNonNull;

public class BitvavoMarketsRequest {

    private final BitvavoMarket market;

    public BitvavoMarketsRequest(Builder builder) {
        this.market = requireNonNull(builder.market);
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

        public BitvavoMarketsRequest build() {
            return new BitvavoMarketsRequest(this);
        }
    }
}
