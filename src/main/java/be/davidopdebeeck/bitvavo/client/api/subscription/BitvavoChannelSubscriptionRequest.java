package be.davidopdebeeck.bitvavo.client.api.subscription;

import be.davidopdebeeck.bitvavo.client.api.market.BitvavoMarket;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class BitvavoChannelSubscriptionRequest {

    private final String name;
    private final List<BitvavoMarket> markets;

    private BitvavoChannelSubscriptionRequest(Builder builder) {
        name = requireNonNull(builder.name);
        markets = requireNonNull(builder.markets);
    }

    public String getName() {
        return name;
    }

    public List<BitvavoMarket> getMarkets() {
        return markets;
    }

    public static final class Builder {

        private String name;
        private List<BitvavoMarket> markets;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withMarkets(List<BitvavoMarket> markets) {
            this.markets = markets;
            return this;
        }

        public BitvavoChannelSubscriptionRequest build() {
            return new BitvavoChannelSubscriptionRequest(this);
        }
    }
}
