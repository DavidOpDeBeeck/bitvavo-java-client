package be.davidopdebeeck.bitvavo.client.api.subscription.trades;

import be.davidopdebeeck.bitvavo.client.api.market.BitvavoMarket;

import java.math.BigDecimal;

import static java.util.Objects.requireNonNull;

public class BitvavoTradesSubscriptionResponse {

    private BitvavoMarket market;
    private long timestamp;
    private String id;
    private BigDecimal amount;
    private BigDecimal price;
    private String side;

    private BitvavoTradesSubscriptionResponse() {
    }

    private BitvavoTradesSubscriptionResponse(Builder builder) {
        market = requireNonNull(builder.market);
        timestamp = requireNonNull(builder.timestamp);
        id = requireNonNull(builder.id);
        amount = requireNonNull(builder.amount);
        price = requireNonNull(builder.price);
        side = requireNonNull(builder.side);
    }

    public BitvavoMarket getMarket() {
        return market;
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

        private BitvavoMarket market;
        private Long timestamp;
        private String id;
        private BigDecimal amount;
        private BigDecimal price;
        private String side;

        public Builder withMarket(BitvavoMarket market) {
            this.market = market;
            return this;
        }

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

        public BitvavoTradesSubscriptionResponse build() {
            return new BitvavoTradesSubscriptionResponse(this);
        }
    }
}
