package be.davidopdebeeck.bitvavo.client.api.side;

import com.fasterxml.jackson.annotation.JsonValue;

public enum BitvavoSide {
    BUY("buy"),
    SELL("sell");

    private final String identifier;

    BitvavoSide(String identifier) {
        this.identifier = identifier;
    }

    @JsonValue
    public String getIdentifier() {
        return identifier;
    }
}
