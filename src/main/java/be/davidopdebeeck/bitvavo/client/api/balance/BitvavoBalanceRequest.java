package be.davidopdebeeck.bitvavo.client.api.balance;

import be.davidopdebeeck.bitvavo.client.api.asset.BitvavoAssetSymbol;

import java.util.Optional;

import static java.util.Optional.ofNullable;

public class BitvavoBalanceRequest {

    private final BitvavoAssetSymbol symbol;

    private BitvavoBalanceRequest(Builder builder) {
        this.symbol = builder.symbol;
    }

    public Optional<BitvavoAssetSymbol> getSymbol() {
        return ofNullable(symbol);
    }

    public static final class Builder {

        private BitvavoAssetSymbol symbol;

        public Builder withSymbol(BitvavoAssetSymbol symbol) {
            this.symbol = symbol;
            return this;
        }

        public BitvavoBalanceRequest build() {
            return new BitvavoBalanceRequest(this);
        }
    }
}
