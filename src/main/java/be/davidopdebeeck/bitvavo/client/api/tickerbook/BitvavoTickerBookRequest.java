package be.davidopdebeeck.bitvavo.client.api.tickerbook;

import be.davidopdebeeck.bitvavo.client.api.market.BitvavoMarket;

import static java.util.Objects.requireNonNull;

public class BitvavoTickerBookRequest {

    private final BitvavoMarket market;

    private BitvavoTickerBookRequest(Builder builder) {
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

        public BitvavoTickerBookRequest build() {
            return new BitvavoTickerBookRequest(this);
        }
    }
}
