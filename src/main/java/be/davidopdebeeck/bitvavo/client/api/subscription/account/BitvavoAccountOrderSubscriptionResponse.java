package be.davidopdebeeck.bitvavo.client.api.subscription.account;

import be.davidopdebeeck.bitvavo.client.api.market.BitvavoMarket;

import java.math.BigDecimal;

import static java.util.Objects.requireNonNull;

public class BitvavoAccountOrderSubscriptionResponse {

    private String orderId;
    private BitvavoMarket market;
    private long created;
    private long updated;
    private String status;
    private String side;
    private String orderType;
    private BigDecimal amount;
    private BigDecimal amountRemaining;
    private BigDecimal price;
    private BigDecimal onHold;
    private String onHoldCurrency;
    private BigDecimal triggerPrice;
    private BigDecimal triggerAmount;
    private String triggerType;
    private String triggerReference;
    private String timeInForce;
    private boolean postOnly;
    private String selfTradePrevention;
    private boolean visible;

    private BitvavoAccountOrderSubscriptionResponse() {
    }

    private BitvavoAccountOrderSubscriptionResponse(Builder builder) {
        orderId = requireNonNull(builder.orderId);
        market = requireNonNull(builder.market);
        created = requireNonNull(builder.created);
        updated = requireNonNull(builder.updated);
        status = requireNonNull(builder.status);
        side = requireNonNull(builder.side);
        orderType = requireNonNull(builder.orderType);
        amount = requireNonNull(builder.amount);
        amountRemaining = requireNonNull(builder.amountRemaining);
        price = requireNonNull(builder.price);
        onHold = requireNonNull(builder.onHold);
        onHoldCurrency = requireNonNull(builder.onHoldCurrency);
        triggerPrice = requireNonNull(builder.triggerPrice);
        triggerAmount = requireNonNull(builder.triggerAmount);
        triggerType = requireNonNull(builder.triggerType);
        triggerReference = requireNonNull(builder.triggerReference);
        timeInForce = requireNonNull(builder.timeInForce);
        postOnly = requireNonNull(builder.postOnly);
        selfTradePrevention = requireNonNull(builder.selfTradePrevention);
        visible = requireNonNull(builder.visible);
    }

    public String getOrderId() {
        return orderId;
    }

    public BitvavoMarket getMarket() {
        return market;
    }

    public long getCreated() {
        return created;
    }

    public long getUpdated() {
        return updated;
    }

    public String getStatus() {
        return status;
    }

    public String getSide() {
        return side;
    }

    public String getOrderType() {
        return orderType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getAmountRemaining() {
        return amountRemaining;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getOnHold() {
        return onHold;
    }

    public String getOnHoldCurrency() {
        return onHoldCurrency;
    }

    public BigDecimal getTriggerPrice() {
        return triggerPrice;
    }

    public BigDecimal getTriggerAmount() {
        return triggerAmount;
    }

    public String getTriggerType() {
        return triggerType;
    }

    public String getTriggerReference() {
        return triggerReference;
    }

    public String getTimeInForce() {
        return timeInForce;
    }

    public boolean isPostOnly() {
        return postOnly;
    }

    public String getSelfTradePrevention() {
        return selfTradePrevention;
    }

    public boolean isVisible() {
        return visible;
    }

    public static final class Builder {

        private String orderId;
        private BitvavoMarket market;
        private Long created;
        private Long updated;
        private String status;
        private String side;
        private String orderType;
        private BigDecimal amount;
        private BigDecimal amountRemaining;
        private BigDecimal price;
        private BigDecimal onHold;
        private String onHoldCurrency;
        private BigDecimal triggerPrice;
        private BigDecimal triggerAmount;
        private String triggerType;
        private String triggerReference;
        private String timeInForce;
        private Boolean postOnly;
        private String selfTradePrevention;
        private Boolean visible;

        public Builder withOrderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder withMarket(BitvavoMarket market) {
            this.market = market;
            return this;
        }

        public Builder withCreated(long created) {
            this.created = created;
            return this;
        }

        public Builder withUpdated(long updated) {
            this.updated = updated;
            return this;
        }

        public Builder withStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder withSide(String side) {
            this.side = side;
            return this;
        }

        public Builder withOrderType(String orderType) {
            this.orderType = orderType;
            return this;
        }

        public Builder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder withAmountRemaining(BigDecimal amountRemaining) {
            this.amountRemaining = amountRemaining;
            return this;
        }

        public Builder withPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder withOnHold(BigDecimal onHold) {
            this.onHold = onHold;
            return this;
        }

        public Builder withOnHoldCurrency(String onHoldCurrency) {
            this.onHoldCurrency = onHoldCurrency;
            return this;
        }

        public Builder withTriggerPrice(BigDecimal triggerPrice) {
            this.triggerPrice = triggerPrice;
            return this;
        }

        public Builder withTriggerAmount(BigDecimal triggerAmount) {
            this.triggerAmount = triggerAmount;
            return this;
        }

        public Builder withTriggerType(String triggerType) {
            this.triggerType = triggerType;
            return this;
        }

        public Builder withTriggerReference(String triggerReference) {
            this.triggerReference = triggerReference;
            return this;
        }

        public Builder withTimeInForce(String timeInForce) {
            this.timeInForce = timeInForce;
            return this;
        }

        public Builder withPostOnly(boolean postOnly) {
            this.postOnly = postOnly;
            return this;
        }

        public Builder withSelfTradePrevention(String selfTradePrevention) {
            this.selfTradePrevention = selfTradePrevention;
            return this;
        }

        public Builder withVisible(boolean visible) {
            this.visible = visible;
            return this;
        }

        public BitvavoAccountOrderSubscriptionResponse build() {
            return new BitvavoAccountOrderSubscriptionResponse(this);
        }
    }
}
