package be.davidopdebeeck.bitvavo.client.api.subscription.trades;

import be.davidopdebeeck.bitvavo.client.api.market.BitvavoMarket;
import be.davidopdebeeck.bitvavo.client.api.side.BitvavoSide;

import java.math.BigDecimal;
import java.time.Instant;

import static java.util.Objects.requireNonNull;

public class BitvavoTradesSubscriptionResponse {

    private BitvavoMarket market;
    private Instant timestamp;
    private String id;
    private BigDecimal amount;
    private BigDecimal price;
    private BitvavoSide side;

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

        private BitvavoMarket market;
        private Instant timestamp;
        private String id;
        private BigDecimal amount;
        private BigDecimal price;
        private BitvavoSide side;

        public Builder withMarket(BitvavoMarket market) {
            this.market = market;
            return this;
        }

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

        public BitvavoTradesSubscriptionResponse build() {
            return new BitvavoTradesSubscriptionResponse(this);
        }
    }
}
