package be.davidopdebeeck.bitvavo.client.api.subscription.account;

import be.davidopdebeeck.bitvavo.client.api.market.BitvavoMarket;
import be.davidopdebeeck.bitvavo.client.api.order.BitvavoOrderStatus;
import be.davidopdebeeck.bitvavo.client.api.ordertypes.BitvavoOrderType;
import be.davidopdebeeck.bitvavo.client.api.selftradeprevention.BitvavoSelfTradePrevention;
import be.davidopdebeeck.bitvavo.client.api.side.BitvavoSide;
import be.davidopdebeeck.bitvavo.client.api.timeinforce.BitvavoTimeInForce;
import be.davidopdebeeck.bitvavo.client.api.trigger.BitvavoTriggerReference;
import be.davidopdebeeck.bitvavo.client.api.trigger.BitvavoTriggerType;

import java.math.BigDecimal;
import java.time.Instant;

import static java.util.Objects.requireNonNull;

public class BitvavoAccountOrderSubscriptionResponse {

    private String orderId;
    private BitvavoMarket market;
    private Instant created;
    private Instant updated;
    private BitvavoOrderStatus status;
    private BitvavoSide side;
    private BitvavoOrderType orderType;
    private BigDecimal amount;
    private BigDecimal amountRemaining;
    private BigDecimal price;
    private BigDecimal onHold;
    private String onHoldCurrency;
    private BigDecimal triggerPrice;
    private BigDecimal triggerAmount;
    private BitvavoTriggerType triggerType;
    private BitvavoTriggerReference triggerReference;
    private BitvavoTimeInForce timeInForce;
    private boolean postOnly;
    private BitvavoSelfTradePrevention selfTradePrevention;
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

    public Instant getCreated() {
        return created;
    }

    public Instant getUpdated() {
        return updated;
    }

    public BitvavoOrderStatus getStatus() {
        return status;
    }

    public BitvavoSide getSide() {
        return side;
    }

    public BitvavoOrderType getOrderType() {
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

    public BitvavoTriggerType getTriggerType() {
        return triggerType;
    }

    public BitvavoTriggerReference getTriggerReference() {
        return triggerReference;
    }

    public BitvavoTimeInForce getTimeInForce() {
        return timeInForce;
    }

    public boolean isPostOnly() {
        return postOnly;
    }

    public BitvavoSelfTradePrevention getSelfTradePrevention() {
        return selfTradePrevention;
    }

    public boolean isVisible() {
        return visible;
    }

    public static final class Builder {

        private String orderId;
        private BitvavoMarket market;
        private Instant created;
        private Instant updated;
        private BitvavoOrderStatus status;
        private BitvavoSide side;
        private BitvavoOrderType orderType;
        private BigDecimal amount;
        private BigDecimal amountRemaining;
        private BigDecimal price;
        private BigDecimal onHold;
        private String onHoldCurrency;
        private BigDecimal triggerPrice;
        private BigDecimal triggerAmount;
        private BitvavoTriggerType triggerType;
        private BitvavoTriggerReference triggerReference;
        private BitvavoTimeInForce timeInForce;
        private Boolean postOnly;
        private BitvavoSelfTradePrevention selfTradePrevention;
        private Boolean visible;

        public Builder withOrderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder withMarket(BitvavoMarket market) {
            this.market = market;
            return this;
        }

        public Builder withCreated(Instant created) {
            this.created = created;
            return this;
        }

        public Builder withUpdated(Instant updated) {
            this.updated = updated;
            return this;
        }

        public Builder withStatus(BitvavoOrderStatus status) {
            this.status = status;
            return this;
        }

        public Builder withSide(BitvavoSide side) {
            this.side = side;
            return this;
        }

        public Builder withOrderType(BitvavoOrderType orderType) {
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

        public Builder withTriggerType(BitvavoTriggerType triggerType) {
            this.triggerType = triggerType;
            return this;
        }

        public Builder withTriggerReference(BitvavoTriggerReference triggerReference) {
            this.triggerReference = triggerReference;
            return this;
        }

        public Builder withTimeInForce(BitvavoTimeInForce timeInForce) {
            this.timeInForce = timeInForce;
            return this;
        }

        public Builder withPostOnly(boolean postOnly) {
            this.postOnly = postOnly;
            return this;
        }

        public Builder withSelfTradePrevention(BitvavoSelfTradePrevention selfTradePrevention) {
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
