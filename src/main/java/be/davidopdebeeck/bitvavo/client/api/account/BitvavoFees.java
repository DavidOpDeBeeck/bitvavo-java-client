package be.davidopdebeeck.bitvavo.client.api.account;

import java.math.BigDecimal;

import static java.util.Objects.requireNonNull;

public class BitvavoFees {

    private BigDecimal taker;
    private BigDecimal maker;
    private BigDecimal volume;

    private BitvavoFees() {
    }

    private BitvavoFees(Builder builder) {
        taker = requireNonNull(builder.taker);
        maker = requireNonNull(builder.maker);
        volume = requireNonNull(builder.volume);
    }

    public BigDecimal getTaker() {
        return taker;
    }

    public BigDecimal getMaker() {
        return maker;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public static final class Builder {

        private BigDecimal taker;
        private BigDecimal maker;
        private BigDecimal volume;

        public Builder withTaker(BigDecimal taker) {
            this.taker = taker;
            return this;
        }

        public Builder withMaker(BigDecimal maker) {
            this.maker = maker;
            return this;
        }

        public Builder withVolume(BigDecimal volume) {
            this.volume = volume;
            return this;
        }

        public BitvavoFees build() {
            return new BitvavoFees(this);
        }
    }
}
