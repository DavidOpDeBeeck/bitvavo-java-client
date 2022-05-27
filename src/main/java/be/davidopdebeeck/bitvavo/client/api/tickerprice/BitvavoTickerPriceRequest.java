package be.davidopdebeeck.bitvavo.client.api.tickerprice;

import be.davidopdebeeck.bitvavo.client.api.market.BitvavoMarket;

import static java.util.Objects.requireNonNull;

public class BitvavoTickerPriceRequest {

    private final BitvavoMarket market;

    private BitvavoTickerPriceRequest(Builder builder) {
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

        public BitvavoTickerPriceRequest build() {
            return new BitvavoTickerPriceRequest(this);
        }
    }
}
