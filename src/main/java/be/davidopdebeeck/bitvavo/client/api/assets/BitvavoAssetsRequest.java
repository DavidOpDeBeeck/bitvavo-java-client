package be.davidopdebeeck.bitvavo.client.api.assets;

import be.davidopdebeeck.bitvavo.client.api.asset.BitvavoAssetSymbol;

import static java.util.Objects.requireNonNull;

public class BitvavoAssetsRequest {

    private final BitvavoAssetSymbol symbol;

    public BitvavoAssetsRequest(Builder builder) {
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

        public BitvavoAssetsRequest build() {
            return new BitvavoAssetsRequest(this);
        }
    }
}
