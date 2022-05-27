package be.davidopdebeeck.bitvavo.client.api.market.book;

import java.math.BigDecimal;

import static java.util.Objects.requireNonNull;

public class BitvavoAsk {

    private final BigDecimal price;
    private final BigDecimal size;

    public BitvavoAsk(Builder builder) {
        this.price = requireNonNull(builder.price);
        this.size = requireNonNull(builder.size);
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getSize() {
        return size;
    }

    public static final class Builder {

        private BigDecimal price;
        private BigDecimal size;

        public Builder withPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder withSize(BigDecimal size) {
            this.size = size;
            return this;
        }

        public BitvavoAsk build() {
            return new BitvavoAsk(this);
        }
    }
}
