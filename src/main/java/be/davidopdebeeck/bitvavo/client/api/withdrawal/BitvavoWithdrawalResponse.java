package be.davidopdebeeck.bitvavo.client.api.withdrawal;

import be.davidopdebeeck.bitvavo.client.api.asset.BitvavoAssetSymbol;

import java.math.BigDecimal;

import static java.util.Objects.requireNonNull;

public class BitvavoWithdrawalResponse {

    private boolean success;
    private BitvavoAssetSymbol symbol;
    private BigDecimal amount;

    private BitvavoWithdrawalResponse() {
    }

    private BitvavoWithdrawalResponse(Builder builder) {
        success = requireNonNull(builder.success);
        symbol = requireNonNull(builder.symbol);
        amount = requireNonNull(builder.amount);
    }

    public boolean isSuccess() {
        return success;
    }

    public BitvavoAssetSymbol getSymbol() {
        return symbol;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public static final class Builder {

        private Boolean success;
        private BitvavoAssetSymbol symbol;
        private BigDecimal amount;

        public Builder withSuccess(boolean success) {
            this.success = success;
            return this;
        }

        public Builder withSymbol(BitvavoAssetSymbol symbol) {
            this.symbol = symbol;
            return this;
        }

        public Builder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public BitvavoWithdrawalResponse build() {
            return new BitvavoWithdrawalResponse(this);
        }
    }
}
