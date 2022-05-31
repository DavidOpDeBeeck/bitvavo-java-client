package be.davidopdebeeck.bitvavo.client.api.trigger;

import com.fasterxml.jackson.annotation.JsonValue;

public enum BitvavoTriggerReference {
    LAST_TRADE("lastTrade"),
    BEST_BID("bestBid"),
    BEST_ASK("bestAsk"),
    MID_PRICE("midPrice");

    private final String identifier;

    BitvavoTriggerReference(String identifier) {
        this.identifier = identifier;
    }

    @JsonValue
    public String getIdentifier() {
        return identifier;
    }
}
