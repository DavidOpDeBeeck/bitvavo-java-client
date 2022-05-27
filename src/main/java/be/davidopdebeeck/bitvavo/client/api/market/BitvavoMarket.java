package be.davidopdebeeck.bitvavo.client.api.market;

import be.davidopdebeeck.bitvavo.client.api.asset.BitvavoAssetSymbol;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class BitvavoMarket {

    public static BitvavoMarket market(BitvavoAssetSymbol baseAsset, BitvavoAssetSymbol quoteAsset) {
        return new BitvavoMarket(baseAsset, quoteAsset);
    }

    private final BitvavoAssetSymbol baseAsset;
    private final BitvavoAssetSymbol quoteAsset;

    private BitvavoMarket(BitvavoAssetSymbol baseAsset, BitvavoAssetSymbol quoteAsset) {
        this.baseAsset = requireNonNull(baseAsset);
        this.quoteAsset = requireNonNull(quoteAsset);
    }

    public BitvavoAssetSymbol getBaseAsset() {
        return baseAsset;
    }

    public BitvavoAssetSymbol getQuoteAsset() {
        return quoteAsset;
    }

    @Override
    public String toString() {
        return format("%s-%s", baseAsset, quoteAsset);
    }
}
