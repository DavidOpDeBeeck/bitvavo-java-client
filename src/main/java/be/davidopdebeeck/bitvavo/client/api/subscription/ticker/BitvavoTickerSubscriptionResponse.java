package be.davidopdebeeck.bitvavo.client.api.subscription.ticker;

import be.davidopdebeeck.bitvavo.client.api.market.BitvavoMarket;

import java.math.BigDecimal;
import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;

public class BitvavoTickerSubscriptionResponse {

    private BitvavoMarket market;
    private BigDecimal bestBid;
    private BigDecimal bestBidSize;
    private BigDecimal bestAsk;
    private BigDecimal bestAskSize;
    private BigDecimal lastPrice;

    private BitvavoTickerSubscriptionResponse() {
    }

    private BitvavoTickerSubscriptionResponse(Builder builder) {
        market = requireNonNull(builder.market);
        bestBid = builder.bestBid;
        bestBidSize = builder.bestBidSize;
        bestAsk = builder.bestAsk;
        bestAskSize = builder.bestAskSize;
        lastPrice = builder.lastPrice;
    }

    public BitvavoMarket getMarket() {
        return market;
    }

    public Optional<BigDecimal> getBestBid() {
        return ofNullable(bestBid);
    }

    public Optional<BigDecimal> getBestBidSize() {
        return ofNullable(bestBidSize);
    }

    public Optional<BigDecimal> getBestAsk() {
        return ofNullable(bestAsk);
    }

    public Optional<BigDecimal> getBestAskSize() {
        return ofNullable(bestAskSize);
    }

    public Optional<BigDecimal> getLastPrice() {
        return ofNullable(lastPrice);
    }

    public static final class Builder {

        private BitvavoMarket market;
        private BigDecimal bestBid;
        private BigDecimal bestBidSize;
        private BigDecimal bestAsk;
        private BigDecimal bestAskSize;
        private BigDecimal lastPrice;

        public Builder withMarket(BitvavoMarket market) {
            this.market = market;
            return this;
        }

        public Builder withBestBid(BigDecimal bestBid) {
            this.bestBid = bestBid;
            return this;
        }

        public Builder withBestBidSize(BigDecimal bestBidSize) {
            this.bestBidSize = bestBidSize;
            return this;
        }

        public Builder withBestAsk(BigDecimal bestAsk) {
            this.bestAsk = bestAsk;
            return this;
        }

        public Builder withBestAskSize(BigDecimal bestAskSize) {
            this.bestAskSize = bestAskSize;
            return this;
        }

        public Builder withLastPrice(BigDecimal lastPrice) {
            this.lastPrice = lastPrice;
            return this;
        }

        public BitvavoTickerSubscriptionResponse build() {
            return new BitvavoTickerSubscriptionResponse(this);
        }
    }
}
