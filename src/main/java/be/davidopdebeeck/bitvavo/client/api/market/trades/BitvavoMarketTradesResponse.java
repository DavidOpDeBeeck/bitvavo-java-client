package be.davidopdebeeck.bitvavo.client.api.market.trades;

import java.math.BigDecimal;

import static java.util.Objects.requireNonNull;

public class BitvavoMarketTradesResponse {

    private long timestamp;
    private String id;
    private BigDecimal amount;
    private BigDecimal price;
    private String side;

    private BitvavoMarketTradesResponse() {
    }

    private BitvavoMarketTradesResponse(Builder builder) {
        timestamp = requireNonNull(builder.timestamp);
        id = requireNonNull(builder.id);
        amount = requireNonNull(builder.amount);
        price = requireNonNull(builder.price);
        side = requireNonNull(builder.side);
    }

    public long getTimestamp() {
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

    public String getSide() {
        return side;
    }

    public static final class Builder {

        private Long timestamp;
        private String id;
        private BigDecimal amount;
        private BigDecimal price;
        private String side;

        public Builder withTimestamp(long timestamp) {
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

        public Builder withSide(String side) {
            this.side = side;
            return this;
        }

        public BitvavoMarketTradesResponse build() {
            return new BitvavoMarketTradesResponse(this);
        }
    }
}
