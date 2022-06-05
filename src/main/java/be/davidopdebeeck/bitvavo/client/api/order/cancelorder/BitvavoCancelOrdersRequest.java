package be.davidopdebeeck.bitvavo.client.api.order.cancelorder;

import be.davidopdebeeck.bitvavo.client.api.market.BitvavoMarket;

import static java.util.Objects.requireNonNull;

public class BitvavoCancelOrdersRequest {

    private final BitvavoMarket market;

    private BitvavoCancelOrdersRequest(Builder builder) {
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

        public BitvavoCancelOrdersRequest build() {
            return new BitvavoCancelOrdersRequest(this);
        }
    }
}
