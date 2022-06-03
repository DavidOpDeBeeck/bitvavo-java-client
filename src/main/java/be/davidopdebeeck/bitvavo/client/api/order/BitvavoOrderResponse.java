package be.davidopdebeeck.bitvavo.client.api.order;

import be.davidopdebeeck.bitvavo.client.api.market.BitvavoMarket;
import be.davidopdebeeck.bitvavo.client.api.ordertypes.BitvavoOrderType;
import be.davidopdebeeck.bitvavo.client.api.selftradeprevention.BitvavoSelfTradePrevention;
import be.davidopdebeeck.bitvavo.client.api.side.BitvavoSide;
import be.davidopdebeeck.bitvavo.client.api.timeinforce.BitvavoTimeInForce;
import be.davidopdebeeck.bitvavo.client.api.trigger.BitvavoTriggerReference;
import be.davidopdebeeck.bitvavo.client.api.trigger.BitvavoTriggerType;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class BitvavoOrderResponse {

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
    private BigDecimal amountQuote;
    private BigDecimal amountQuoteRemaining;
    private BigDecimal onHold;
    private String onHoldCurrency;
    private BigDecimal triggerPrice;
    private BigDecimal triggerAmount;
    private BitvavoTriggerType triggerType;
    private BitvavoTriggerReference triggerReference;
    private BigDecimal filledAmount;
    private BigDecimal filledAmountQuote;
    private BigDecimal feePaid;
    private String feeCurrency;
    private List<BitvavoFill> fills;
    private BitvavoSelfTradePrevention selfTradePrevention;
    private boolean visible;
    private BitvavoTimeInForce timeInForce;
    private boolean postOnly;
    private boolean disableMarketProtection;

    private BitvavoOrderResponse() {
    }

    private BitvavoOrderResponse(Builder builder) {
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
        amountQuote = requireNonNull(builder.amountQuote);
        amountQuoteRemaining = requireNonNull(builder.amountQuoteRemaining);
        onHold = requireNonNull(builder.onHold);
        onHoldCurrency = requireNonNull(builder.onHoldCurrency);
        triggerPrice = requireNonNull(builder.triggerPrice);
        triggerAmount = requireNonNull(builder.triggerAmount);
        triggerType = requireNonNull(builder.triggerType);
        triggerReference = requireNonNull(builder.triggerReference);
        filledAmount = requireNonNull(builder.filledAmount);
        filledAmountQuote = requireNonNull(builder.filledAmountQuote);
        feePaid = requireNonNull(builder.feePaid);
        feeCurrency = requireNonNull(builder.feeCurrency);
        fills = requireNonNull(builder.fills);
        selfTradePrevention = requireNonNull(builder.selfTradePrevention);
        visible = requireNonNull(builder.visible);
        timeInForce = requireNonNull(builder.timeInForce);
        postOnly = requireNonNull(builder.postOnly);
        disableMarketProtection = requireNonNull(builder.disableMarketProtection);
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

    public BigDecimal getAmountQuote() {
        return amountQuote;
    }

    public BigDecimal getAmountQuoteRemaining() {
        return amountQuoteRemaining;
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

    public BigDecimal getFilledAmount() {
        return filledAmount;
    }

    public BigDecimal getFilledAmountQuote() {
        return filledAmountQuote;
    }

    public BigDecimal getFeePaid() {
        return feePaid;
    }

    public String getFeeCurrency() {
        return feeCurrency;
    }

    public List<BitvavoFill> getFills() {
        return fills;
    }

    public BitvavoSelfTradePrevention getSelfTradePrevention() {
        return selfTradePrevention;
    }

    public boolean isVisible() {
        return visible;
    }

    public BitvavoTimeInForce getTimeInForce() {
        return timeInForce;
    }

    public boolean isPostOnly() {
        return postOnly;
    }

    public boolean isDisableMarketProtection() {
        return disableMarketProtection;
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
        private BigDecimal amountQuote;
        private BigDecimal amountQuoteRemaining;
        private BigDecimal onHold;
        private String onHoldCurrency;
        private BigDecimal triggerPrice;
        private BigDecimal triggerAmount;
        private BitvavoTriggerType triggerType;
        private BitvavoTriggerReference triggerReference;
        private BigDecimal filledAmount;
        private BigDecimal filledAmountQuote;
        private BigDecimal feePaid;
        private String feeCurrency;
        private List<BitvavoFill> fills;
        private BitvavoSelfTradePrevention selfTradePrevention;
        private Boolean visible;
        private BitvavoTimeInForce timeInForce;
        private Boolean postOnly;
        private Boolean disableMarketProtection;

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

        public Builder withAmountQuote(BigDecimal amountQuote) {
            this.amountQuote = amountQuote;
            return this;
        }

        public Builder withAmountQuoteRemaining(BigDecimal amountQuoteRemaining) {
            this.amountQuoteRemaining = amountQuoteRemaining;
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

        public Builder withFilledAmount(BigDecimal filledAmount) {
            this.filledAmount = filledAmount;
            return this;
        }

        public Builder withFilledAmountQuote(BigDecimal filledAmountQuote) {
            this.filledAmountQuote = filledAmountQuote;
            return this;
        }

        public Builder withFeePaid(BigDecimal feePaid) {
            this.feePaid = feePaid;
            return this;
        }

        public Builder withFeeCurrency(String feeCurrency) {
            this.feeCurrency = feeCurrency;
            return this;
        }

        public Builder withFills(List<BitvavoFill> fills) {
            this.fills = fills;
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

        public Builder withTimeInForce(BitvavoTimeInForce timeInForce) {
            this.timeInForce = timeInForce;
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

        public BitvavoOrderResponse build() {
            return new BitvavoOrderResponse(this);
        }
    }
}
