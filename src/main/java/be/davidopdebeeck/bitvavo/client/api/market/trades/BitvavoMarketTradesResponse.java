package be.davidopdebeeck.bitvavo.client.api.market.trades;

import be.davidopdebeeck.bitvavo.client.api.side.BitvavoSide;

import java.math.BigDecimal;
import java.time.Instant;

import static java.util.Objects.requireNonNull;

public class BitvavoMarketTradesResponse {

    private Instant timestamp;
    private String id;
    private BigDecimal amount;
    private BigDecimal price;
    private BitvavoSide side;

    private BitvavoMarketTradesResponse() {
    }

    private BitvavoMarketTradesResponse(Builder builder) {
        timestamp = requireNonNull(builder.timestamp);
        id = requireNonNull(builder.id);
        amount = requireNonNull(builder.amount);
        price = requireNonNull(builder.price);
        side = requireNonNull(builder.side);
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BitvavoSide getSide() {
        return side;
    }

    public static final class Builder {

        private Instant timestamp;
        private String id;
        private BigDecimal amount;
        private BigDecimal price;
        private BitvavoSide side;

        public Builder withTimestamp(Instant timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder withPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder withSide(BitvavoSide side) {
            this.side = side;
            return this;
        }

        public BitvavoMarketTradesResponse build() {
            return new BitvavoMarketTradesResponse(this);
        }
    }
}
