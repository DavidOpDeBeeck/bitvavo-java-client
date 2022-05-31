package be.davidopdebeeck.bitvavo.client.api.market;

import com.fasterxml.jackson.annotation.JsonValue;

public enum BitvavoMarketStatus {
    TRADING("trading"),
    HALTED("halted"),
    AUCTION("auction");

    private final String identifier;

    BitvavoMarketStatus(String identifier) {
        this.identifier = identifier;
    }

    @JsonValue
    public String getIdentifier() {
        return identifier;
    }
}
