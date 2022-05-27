package be.davidopdebeeck.bitvavo.client.api.tickerbook;

import be.davidopdebeeck.bitvavo.client.api.market.BitvavoMarket;

import java.math.BigDecimal;

import static java.util.Objects.requireNonNull;

public class BitvavoTickerBookResponse {

    private BitvavoMarket market;
    private BigDecimal bid;
    private BigDecimal bidSize;
    private BigDecimal ask;
    private BigDecimal askSize;

    private BitvavoTickerBookResponse() {
    }

    private BitvavoTickerBookResponse(Builder builder) {
        market = requireNonNull(builder.market);
        bid = requireNonNull(builder.bid);
        bidSize = requireNonNull(builder.bidSize);
        ask = requireNonNull(builder.ask);
        askSize = requireNonNull(builder.askSize);
    }

    public BitvavoMarket getMarket() {
        return market;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public BigDecimal getBidSize() {
        return bidSize;
    }

    public BigDecimal getAsk() {
        return ask;
    }

    public BigDecimal getAskSize() {
        return askSize;
    }

    public static final class Builder {

        private BitvavoMarket market;
        private BigDecimal bid;
        private BigDecimal bidSize;
        private BigDecimal ask;
        private BigDecimal askSize;

        public Builder withMarket(BitvavoMarket market) {
            this.market = requireNonNull(market);
            return this;
        }

        public Builder withBid(BigDecimal bid) {
            this.bid = bid;
            return this;
        }

        public Builder withBidSize(BigDecimal bidSize) {
            this.bidSize = bidSize;
            return this;
        }

        public Builder withAsk(BigDecimal ask) {
            this.ask = ask;
            return this;
        }

        public Builder withAskSize(BigDecimal askSize) {
            this.askSize = askSize;
            return this;
        }

        public BitvavoTickerBookResponse build() {
            return new BitvavoTickerBookResponse(this);
        }
    }
}
