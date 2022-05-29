package be.davidopdebeeck.bitvavo.client.api.market.candles;

import java.math.BigDecimal;

import static java.util.Objects.requireNonNull;

public class BitvavoCandle {

    private final Long timestamp;
    private final BigDecimal open;
    private final BigDecimal high;
    private final BigDecimal low;
    private final BigDecimal close;
    private final BigDecimal volume;

    private BitvavoCandle(Builder builder) {
        timestamp = requireNonNull(builder.timestamp);
        open = requireNonNull(builder.open);
        high = requireNonNull(builder.high);
        low = requireNonNull(builder.low);
        close = requireNonNull(builder.close);
        volume = requireNonNull(builder.volume);
    }

    public Long getTimestamp() {
        return timestamp;
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

    public BigDecimal getClose() {
        return close;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public static final class Builder {

        private Long timestamp;
        private BigDecimal open;
        private BigDecimal high;
        private BigDecimal low;
        private BigDecimal close;
        private BigDecimal volume;

        public Builder withTimestamp(Long timestamp) {
            this.timestamp = timestamp;
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

        public Builder withClose(BigDecimal close) {
            this.close = close;
            return this;
        }

        public Builder withVolume(BigDecimal volume) {
            this.volume = volume;
            return this;
        }

        public BitvavoCandle build() {
            return new BitvavoCandle(this);
        }
    }
}
