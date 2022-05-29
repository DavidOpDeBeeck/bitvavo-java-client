package be.davidopdebeeck.bitvavo.client.api.subscription.book;

import be.davidopdebeeck.bitvavo.client.api.market.BitvavoMarket;
import be.davidopdebeeck.bitvavo.client.api.market.book.BitvavoAsk;
import be.davidopdebeeck.bitvavo.client.api.market.book.BitvavoBid;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class BitvavoBookSubscriptionResponse {

    private BitvavoMarket market;
    private int nonce;
    private List<BitvavoBid> bids;
    private List<BitvavoAsk> asks;

    private BitvavoBookSubscriptionResponse() {
    }

    private BitvavoBookSubscriptionResponse(Builder builder) {
        this.market = requireNonNull(builder.market);
        this.nonce = requireNonNull(builder.nonce);
        this.bids = requireNonNull(builder.bids);
        this.asks = requireNonNull(builder.asks);
    }

    public BitvavoMarket getMarket() {
        return market;
    }

    public int getNonce() {
        return nonce;
    }

    public List<BitvavoBid> getBids() {
        return bids;
    }

    public List<BitvavoAsk> getAsks() {
        return asks;
    }

    public static final class Builder {

        private BitvavoMarket market;
        private Integer nonce;
        private List<BitvavoBid> bids;
        private List<BitvavoAsk> asks;

        public Builder withMarket(BitvavoMarket market) {
            this.market = market;
            return this;
        }

        public Builder withNonce(int nonce) {
            this.nonce = nonce;
            return this;
        }

        public Builder withBids(List<BitvavoBid> bids) {
            this.bids = bids;
            return this;
        }

        public Builder withAsks(List<BitvavoAsk> asks) {
            this.asks = asks;
            return this;
        }

        public BitvavoBookSubscriptionResponse build() {
            return new BitvavoBookSubscriptionResponse(this);
        }
    }
}
