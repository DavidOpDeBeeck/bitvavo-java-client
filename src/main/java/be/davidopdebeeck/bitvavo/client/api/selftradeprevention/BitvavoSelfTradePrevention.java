package be.davidopdebeeck.bitvavo.client.api.selftradeprevention;

import com.fasterxml.jackson.annotation.JsonValue;

public enum BitvavoSelfTradePrevention {
    DECREMENT_AND_CANCEL("decrementAndCancel"),
    CANCEL_OLDEST("cancelOldest"),
    CANCEL_NEWEST("cancelNewest"),
    CANCEL_BOTH("cancelBoth");

    private final String identifier;

    BitvavoSelfTradePrevention(String identifier) {
        this.identifier = identifier;
    }

    @JsonValue
    public String getIdentifier() {
        return identifier;
    }
}
