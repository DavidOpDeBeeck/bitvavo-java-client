package be.davidopdebeeck.bitvavo.client.api.trigger;

import com.fasterxml.jackson.annotation.JsonValue;

public enum BitvavoTriggerType {
    PRICE("price");

    private final String identifier;

    BitvavoTriggerType(String identifier) {
        this.identifier = identifier;
    }

    @JsonValue
    public String getIdentifier() {
        return identifier;
    }
}
