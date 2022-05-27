package be.davidopdebeeck.bitvavo.client.api.asset;

import be.davidopdebeeck.bitvavo.client.api.market.BitvavoMarket;

import static java.util.Objects.requireNonNull;

public class BitvavoAssetSymbol implements Comparable<BitvavoAssetSymbol> {

    public static BitvavoAssetSymbol symbol(String value) {
        return new BitvavoAssetSymbol(value);
    }

    private final String value;

    private BitvavoAssetSymbol(String value) {
        this.value = requireNonNull(value);
    }

    public String getValue() {
        return value;
    }

    public BitvavoMarket toMarket(BitvavoAssetSymbol quoteAsset) {
        return BitvavoMarket.market(this, quoteAsset);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BitvavoAssetSymbol that = (BitvavoAssetSymbol) o;

        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public int compareTo(BitvavoAssetSymbol o) {
        return value.compareTo(o.value);
    }
}
