package be.davidopdebeeck.bitvavo.client.api.trades;

import be.davidopdebeeck.bitvavo.client.api.market.BitvavoMarket;

import java.math.BigDecimal;

import static java.util.Objects.requireNonNull;

public class BitvavoTradesResponse {

    private String id;
    private String orderId;
    private long timestamp;
    private BitvavoMarket market;
    private String side;
    private BigDecimal amount;
    private BigDecimal price;
    private boolean taker;
    private BigDecimal fee;
    private String feeCurrency;
    private boolean settled;

    private BitvavoTradesResponse() {
    }

    private BitvavoTradesResponse(Builder builder) {
        id = requireNonNull(builder.id);
        orderId = requireNonNull(builder.orderId);
        timestamp = requireNonNull(builder.timestamp);
        market = requireNonNull(builder.market);
        side = requireNonNull(builder.side);
        amount = requireNonNull(builder.amount);
        price = requireNonNull(builder.price);
        taker = requireNonNull(builder.taker);
        fee = requireNonNull(builder.fee);
        feeCurrency = requireNonNull(builder.feeCurrency);
        settled = requireNonNull(builder.settled);
    }

    public String getId() {
        return id;
    }

    public String getOrderId() {
        return orderId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public BitvavoMarket getMarket() {
        return market;
    }

    public String getSide() {
        return side;
    }

    public BigDecimal getAmount() {
        return amount;
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

    public boolean isSettled() {
        return settled;
    }

    public static final class Builder {

        private String id;
        private String orderId;
        private Long timestamp;
        private BitvavoMarket market;
        private String side;
        private BigDecimal amount;
        private BigDecimal price;
        private Boolean taker;
        private BigDecimal fee;
        private String feeCurrency;
        private Boolean settled;

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withOrderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder withTimestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder withMarket(BitvavoMarket market) {
            this.market = market;
            return this;
        }

        public Builder withSide(String side) {
            this.side = side;
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

        public Builder withSettled(boolean settled) {
            this.settled = settled;
            return this;
        }

        public BitvavoTradesResponse build() {
            return new BitvavoTradesResponse(this);
        }
    }
}
