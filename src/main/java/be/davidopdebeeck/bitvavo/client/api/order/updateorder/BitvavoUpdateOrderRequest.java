package be.davidopdebeeck.bitvavo.client.api.order.updateorder;

import be.davidopdebeeck.bitvavo.client.api.market.BitvavoMarket;
import be.davidopdebeeck.bitvavo.client.api.selftradeprevention.BitvavoSelfTradePrevention;
import be.davidopdebeeck.bitvavo.client.api.timeinforce.BitvavoTimeInForce;

import java.math.BigDecimal;
import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;

// TODO: split this into separate classes for each orderType to validate required fields
public class BitvavoUpdateOrderRequest {

    private final BitvavoMarket market;
    private final String orderId;
    private final BigDecimal amount;
    private final BigDecimal amountRemaining;
    private final BigDecimal price;
    private final BigDecimal triggerAmount;
    private final BitvavoTimeInForce timeInForce;
    private final BitvavoSelfTradePrevention selfTradePrevention;
    private final Boolean postOnly;
    private final boolean responseRequired;

    private BitvavoUpdateOrderRequest(Builder builder) {
        market = requireNonNull(builder.market);
        orderId = requireNonNull(builder.orderId);
        amount = builder.amount;
        amountRemaining = builder.amountRemaining;
        price = builder.price;
        triggerAmount = builder.triggerAmount;
        timeInForce = builder.timeInForce;
        selfTradePrevention = requireNonNull(builder.selfTradePrevention);
        postOnly = builder.postOnly;
        // TODO we currently do not support different response types on the same endpoint
        responseRequired = true;
    }

    public BitvavoMarket getMarket() {
        return market;
    }

    public String getOrderId() {
        return orderId;
    }

    public Optional<BigDecimal> getAmount() {
        return ofNullable(amount);
    }

    public Optional<BigDecimal> getAmountRemaining() {
        return ofNullable(amountRemaining);
    }

    public Optional<BigDecimal> getPrice() {
        return ofNullable(price);
    }

    public Optional<BigDecimal> getTriggerAmount() {
        return ofNullable(triggerAmount);
    }

    public Optional<BitvavoTimeInForce> getTimeInForce() {
        return ofNullable(timeInForce);
    }

    public BitvavoSelfTradePrevention getSelfTradePrevention() {
        return selfTradePrevention;
    }

    public Optional<Boolean> getPostOnly() {
        return ofNullable(postOnly);
    }

    public boolean isResponseRequired() {
        return responseRequired;
    }

    public static final class Builder {

        private BitvavoMarket market;
        private String orderId;
        private BigDecimal amount;
        private BigDecimal amountRemaining;
        private BigDecimal price;
        private BigDecimal triggerAmount;
        private BitvavoTimeInForce timeInForce;
        private BitvavoSelfTradePrevention selfTradePrevention;
        private Boolean postOnly;

        public Builder withMarket(BitvavoMarket market) {
            this.market = market;
            return this;
        }

        public Builder withOrderId(String orderId) {
            this.orderId = orderId;
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

        public Builder withTriggerAmount(BigDecimal triggerAmount) {
            this.triggerAmount = triggerAmount;
            return this;
        }

        public Builder withTimeInForce(BitvavoTimeInForce timeInForce) {
            this.timeInForce = timeInForce;
            return this;
        }

        public Builder withSelfTradePrevention(BitvavoSelfTradePrevention selfTradePrevention) {
            this.selfTradePrevention = selfTradePrevention;
            return this;
        }

        public Builder withPostOnly(Boolean postOnly) {
            this.postOnly = postOnly;
            return this;
        }

        public BitvavoUpdateOrderRequest build() {
            return new BitvavoUpdateOrderRequest(this);
        }
    }
}
