package be.davidopdebeeck.bitvavo.client.api.ticker24h;

import be.davidopdebeeck.bitvavo.client.api.market.BitvavoMarket;

import static java.util.Objects.requireNonNull;

public class BitvavoTicker24hRequest {

    private final BitvavoMarket market;

    private BitvavoTicker24hRequest(Builder builder) {
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

        public BitvavoTicker24hRequest build() {
            return new BitvavoTicker24hRequest(this);
        }
    }
}
