package be.davidopdebeeck.bitvavo.client.api.balance;

import be.davidopdebeeck.bitvavo.client.api.asset.BitvavoAssetSymbol;

import java.math.BigDecimal;

import static java.util.Objects.requireNonNull;

public class BitvavoBalanceResponse {

    private BitvavoAssetSymbol symbol;
    private BigDecimal available;
    private BigDecimal inOrder;

    private BitvavoBalanceResponse() {
    }

    private BitvavoBalanceResponse(Builder builder) {
        symbol = requireNonNull(builder.symbol);
        available = requireNonNull(builder.available);
        inOrder = requireNonNull(builder.inOrder);
    }

    public BitvavoAssetSymbol getSymbol() {
        return symbol;
    }

    public BigDecimal getAvailable() {
        return available;
    }

    public BigDecimal getInOrder() {
        return inOrder;
    }

    public static final class Builder {

        private BitvavoAssetSymbol symbol;
        private BigDecimal available;
        private BigDecimal inOrder;

        public Builder withSymbol(BitvavoAssetSymbol symbol) {
            this.symbol = symbol;
            return this;
        }

        public Builder withAvailable(BigDecimal available) {
            this.available = available;
            return this;
        }

        public Builder withInOrder(BigDecimal inOrder) {
            this.inOrder = inOrder;
            return this;
        }

        public BitvavoBalanceResponse build() {
            return new BitvavoBalanceResponse(this);
        }
    }
}
