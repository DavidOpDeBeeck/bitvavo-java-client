package be.davidopdebeeck.bitvavo.client.api.ticker24h;

import be.davidopdebeeck.bitvavo.client.api.market.BitvavoMarket;

import java.math.BigDecimal;
import java.time.Instant;

import static java.util.Objects.requireNonNull;

public class BitvavoTicker24hResponse {

    private BitvavoMarket market;
    private BigDecimal open;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal last;
    private BigDecimal volume;
    private BigDecimal volumeQuote;
    private BigDecimal bid;
    private BigDecimal bidSize;
    private BigDecimal ask;
    private BigDecimal askSize;
    private Instant timestamp;

    private BitvavoTicker24hResponse() {
    }

    private BitvavoTicker24hResponse(Builder builder) {
        market = requireNonNull(builder.market);
        open = requireNonNull(builder.open);
        high = requireNonNull(builder.high);
        low = requireNonNull(builder.low);
        last = requireNonNull(builder.last);
        volume = requireNonNull(builder.volume);
        volumeQuote = requireNonNull(builder.volumeQuote);
        bid = requireNonNull(builder.bid);
        bidSize = requireNonNull(builder.bidSize);
        ask = requireNonNull(builder.ask);
        askSize = requireNonNull(builder.askSize);
        timestamp = requireNonNull(builder.timestamp);
    }

    public BitvavoMarket getMarket() {
        return market;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public BigDecimal getLow() {
        return low;
    }

    public BigDecimal getLast() {
        return last;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public BigDecimal getVolumeQuote() {
        return volumeQuote;
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

    public Instant getTimestamp() {
        return timestamp;
    }

    public static final class Builder {

        private BitvavoMarket market;
        private BigDecimal open;
        private BigDecimal high;
        private BigDecimal low;
        private BigDecimal last;
        private BigDecimal volume;
        private BigDecimal volumeQuote;
        private BigDecimal bid;
        private BigDecimal bidSize;
        private BigDecimal ask;
        private BigDecimal askSize;
        private Instant timestamp;

        public Builder withMarket(BitvavoMarket market) {
            this.market = market;
            return this;
        }

        public Builder withOpen(BigDecimal open) {
            this.open = open;
            return this;
        }

        public Builder withHigh(BigDecimal high) {
            this.high = high;
            return this;
        }

        public Builder withLow(BigDecimal low) {
            this.low = low;
            return this;
        }

        public Builder withLast(BigDecimal last) {
            this.last = last;
            return this;
        }

        public Builder withVolume(BigDecimal volume) {
            this.volume = volume;
            return this;
        }

        public Builder withVolumeQuote(BigDecimal volumeQuote) {
            this.volumeQuote = volumeQuote;
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

        public Builder withTimestamp(Instant timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public BitvavoTicker24hResponse build() {
            return new BitvavoTicker24hResponse(this);
        }
    }
}
