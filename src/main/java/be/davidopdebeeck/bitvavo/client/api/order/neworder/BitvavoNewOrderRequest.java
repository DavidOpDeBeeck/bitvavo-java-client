package be.davidopdebeeck.bitvavo.client.api.order.neworder;

import be.davidopdebeeck.bitvavo.client.api.market.BitvavoMarket;
import be.davidopdebeeck.bitvavo.client.api.ordertypes.BitvavoOrderType;
import be.davidopdebeeck.bitvavo.client.api.selftradeprevention.BitvavoSelfTradePrevention;
import be.davidopdebeeck.bitvavo.client.api.side.BitvavoSide;
import be.davidopdebeeck.bitvavo.client.api.timeinforce.BitvavoTimeInForce;
import be.davidopdebeeck.bitvavo.client.api.trigger.BitvavoTriggerReference;
import be.davidopdebeeck.bitvavo.client.api.trigger.BitvavoTriggerType;

import java.math.BigDecimal;
import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;

// TODO: split this into separate classes for each orderType to validate required fields
public class BitvavoNewOrderRequest {

    private final BitvavoMarket market;
    private final BitvavoSide side;
    private final BitvavoOrderType orderType;
    private final BigDecimal amount;
    private final BigDecimal price;
    private final BigDecimal amountQuote;
    private final BigDecimal triggerAmount;
    private final BitvavoTriggerType triggerType;
    private final BitvavoTriggerReference triggerReference;
    private final BitvavoTimeInForce timeInForce;
    private final BitvavoSelfTradePrevention selfTradePrevention;
    private final Boolean postOnly;
    private final Boolean disableMarketProtection;
    private final boolean responseRequired;

    private BitvavoNewOrderRequest(Builder builder) {
        market = requireNonNull(builder.market);
        side = requireNonNull(builder.side);
        orderType = requireNonNull(builder.orderType);
        amount = builder.amount;
        price = builder.price;
        amountQuote = builder.amountQuote;
        triggerAmount = builder.triggerAmount;
        triggerType = builder.triggerType;
        triggerReference = builder.triggerReference;
        timeInForce = builder.timeInForce;
        selfTradePrevention = requireNonNull(builder.selfTradePrevention);
        postOnly = builder.postOnly;
        disableMarketProtection = builder.disableMarketProtection;
        // TODO we currently do not support different response types on the same endpoint
        responseRequired = true;
    }

    public BitvavoMarket getMarket() {
        return market;
    }

    public BitvavoSide getSide() {
        return side;
    }

    public Optional<BigDecimal> getAmount() {
        return ofNullable(amount);
    }

    public Optional<BigDecimal> getPrice() {
        return ofNullable(price);
    }

    public Optional<BigDecimal> getAmountQuote() {
        return ofNullable(amountQuote);
    }

    public Optional<BigDecimal> getTriggerAmount() {
        return ofNullable(triggerAmount);
    }

    public Optional<BitvavoTriggerType> getTriggerType() {
        return ofNullable(triggerType);
    }

    public Optional<BitvavoTriggerReference> getTriggerReference() {
        return ofNullable(triggerReference);
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

    public Optional<Boolean> getDisableMarketProtection() {
        return ofNullable(disableMarketProtection);
    }

    public boolean isResponseRequired() {
        return responseRequired;
    }

    public static final class Builder {

        private BitvavoMarket market;
        private BitvavoSide side;
        private BitvavoOrderType orderType;
        private BigDecimal amount;
        private BigDecimal price;
        private BigDecimal amountQuote;
        private BigDecimal triggerAmount;
        private BitvavoTriggerType triggerType;
        private BitvavoTriggerReference triggerReference;
        private BitvavoTimeInForce timeInForce;
        private BitvavoSelfTradePrevention selfTradePrevention;
        private Boolean postOnly;
        private Boolean disableMarketProtection;

        public Builder withMarket(BitvavoMarket market) {
            this.market = market;
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

        public Builder withPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder withAmountQuote(BigDecimal amountQuote) {
            this.amountQuote = amountQuote;
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

        public Builder withSelfTradePrevention(BitvavoSelfTradePrevention selfTradePrevention) {
            this.selfTradePrevention = selfTradePrevention;
            return this;
        }

        public Builder withPostOnly(boolean postOnly) {
            this.postOnly = postOnly;
            return this;
        }

        public Builder withDisableMarketProtection(boolean disableMarketProtection) {
            this.disableMarketProtection = disableMarketProtection;
            return this;
        }

        public BitvavoNewOrderRequest build() {
            return new BitvavoNewOrderRequest(this);
        }
    }
}
