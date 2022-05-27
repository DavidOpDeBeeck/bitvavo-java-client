package be.davidopdebeeck.bitvavo.client.api.deposit;

import be.davidopdebeeck.bitvavo.client.api.asset.BitvavoAssetSymbol;

import static java.util.Objects.requireNonNull;

public class BitvavoDepositRequest {

    private final BitvavoAssetSymbol symbol;

    private BitvavoDepositRequest(Builder builder) {
        this.symbol = requireNonNull(builder.symbol);
    }

    public BitvavoAssetSymbol getSymbol() {
        return symbol;
    }

    public static final class Builder {

        private BitvavoAssetSymbol symbol;

        public Builder withSymbol(BitvavoAssetSymbol symbol) {
            this.symbol = symbol;
            return this;
        }

        public BitvavoDepositRequest build() {
            return new BitvavoDepositRequest(this);
        }
    }
}
