package be.davidopdebeeck.bitvavo.client.api.market.book;

import java.util.Optional;

import static java.util.Optional.ofNullable;

public class BitvavoMarketBookRequest {

    private final Integer depth;

    private BitvavoMarketBookRequest(Builder builder) {
        this.depth = builder.depth;
    }

    public Optional<Integer> getDepth() {
        return ofNullable(depth);
    }

    public static final class Builder {

        private Integer depth;

        public Builder withDepth(int depth) {
            this.depth = depth;
            return this;
        }

        public BitvavoMarketBookRequest build() {
            return new BitvavoMarketBookRequest(this);
        }
    }
}
