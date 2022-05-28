package be.davidopdebeeck.bitvavo.client.api.subscription.account;

import be.davidopdebeeck.bitvavo.client.api.market.BitvavoMarket;

import java.math.BigDecimal;

import static java.util.Objects.requireNonNull;

public class BitvavoAccountFillSubscriptionResponse {

    private String orderId;
    private BitvavoMarket market;
    private String fillId;
    private long timestamp;
    private BigDecimal amount;
    private String side;
    private BigDecimal price;
    private boolean taker;
    private BigDecimal fee;
    private String feeCurrency;

    private BitvavoAccountFillSubscriptionResponse() {
    }

    private BitvavoAccountFillSubscriptionResponse(Builder builder) {
        orderId = requireNonNull(builder.orderId);
        market = requireNonNull(builder.market);
        orderId = requireNonNull(builder.orderId);
        fillId = requireNonNull(builder.fillId);
        timestamp = requireNonNull(builder.timestamp);
        amount = requireNonNull(builder.amount);
        side = requireNonNull(builder.side);
        price = requireNonNull(builder.price);
        taker = requireNonNull(builder.taker);
        fee = requireNonNull(builder.fee);
        feeCurrency = requireNonNull(builder.feeCurrency);
    }

    public String getOrderId() {
        return orderId;
    }

    public String getFillId() {
        return fillId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getSide() {
        return side;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isTaker() {
        return taker;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public String getFeeCurrency() {
        return feeCurrency;
    }

    public BitvavoMarket getMarket() {
        return market;
    }

    public static final class Builder {

        private String orderId;
        private String fillId;
        private Long timestamp;
        private BigDecimal amount;
        private String side;
        private BigDecimal price;
        private Boolean taker;
        private BigDecimal fee;
        private String feeCurrency;
        private BitvavoMarket market;

        public Builder withOrderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder withFillId(String fillId) {
            this.fillId = fillId;
            return this;
        }

        public Builder withTimestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder withSide(String side) {
            this.side = side;
            return this;
        }

        public Builder withPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder withTaker(boolean taker) {
            this.taker = taker;
            return this;
        }

        public Builder withFee(BigDecimal fee) {
            this.fee = fee;
            return this;
        }

        public Builder withFeeCurrency(String feeCurrency) {
            this.feeCurrency = feeCurrency;
            return this;
        }

        public BitvavoAccountFillSubscriptionResponse build() {
            return new BitvavoAccountFillSubscriptionResponse(this);
        }

        public Builder withMarket(BitvavoMarket market) {
            this.market = market;
            return this;
        }
    }
}