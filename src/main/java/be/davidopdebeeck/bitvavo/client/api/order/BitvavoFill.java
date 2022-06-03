package be.davidopdebeeck.bitvavo.client.api.order;

import java.math.BigDecimal;
import java.time.Instant;

import static java.util.Objects.requireNonNull;

public class BitvavoFill {

    private String id;
    private Instant timestamp;
    private BigDecimal amount;
    private BigDecimal price;
    private boolean taker;
    private BigDecimal fee;
    private String feeCurrency;
    private boolean settled;

    private BitvavoFill() {
    }

    private BitvavoFill(Builder builder) {
        id = requireNonNull(builder.id);
        timestamp = requireNonNull(builder.timestamp);
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

    public Instant getTimestamp() {
        return timestamp;
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
        private Instant timestamp;
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

        public Builder withTimestamp(Instant timestamp) {
            this.timestamp = timestamp;
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

        public BitvavoFill build() {
            return new BitvavoFill(this);
        }
    }
}
