package be.davidopdebeeck.bitvavo.client.api.withdrawal;

import be.davidopdebeeck.bitvavo.client.api.asset.BitvavoAssetSymbol;

import java.math.BigDecimal;
import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;

public class BitvavoWithdrawalRequest {

    private final BitvavoAssetSymbol symbol;
    private final BigDecimal amount;
    private final String address;
    private final String paymentId;
    private final Boolean internal;
    private final Boolean addWithdrawalFee;

    private BitvavoWithdrawalRequest(Builder builder) {
        symbol = requireNonNull(builder.symbol);
        amount = requireNonNull(builder.amount);
        address = requireNonNull(builder.address);
        paymentId = builder.paymentId;
        internal = builder.internal;
        addWithdrawalFee = builder.addWithdrawalFee;
    }

    public BitvavoAssetSymbol getSymbol() {
        return symbol;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getAddress() {
        return address;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public Optional<Boolean> getInternal() {
        return ofNullable(internal);
    }

    public Optional<Boolean> getAddWithdrawalFee() {
        return ofNullable(addWithdrawalFee);
    }

    public static final class Builder {

        private BitvavoAssetSymbol symbol;
        private BigDecimal amount;
        private String address;
        private String paymentId;
        private Boolean internal;
        private Boolean addWithdrawalFee;

        public Builder withSymbol(BitvavoAssetSymbol symbol) {
            this.symbol = symbol;
            return this;
        }

        public Builder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder withAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder withPaymentId(String paymentId) {
            this.paymentId = paymentId;
            return this;
        }

        public Builder withInternal(Boolean internal) {
            this.internal = internal;
            return this;
        }

        public Builder withAddWithdrawalFee(Boolean addWithdrawalFee) {
            this.addWithdrawalFee = addWithdrawalFee;
            return this;
        }

        public BitvavoWithdrawalRequest build() {
            return new BitvavoWithdrawalRequest(this);
        }
    }
}
