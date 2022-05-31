package be.davidopdebeeck.bitvavo.client.api.ordertypes;

import com.fasterxml.jackson.annotation.JsonValue;

public enum BitvavoOrderType {
    MARKET("market"),
    LIMIT("limit"),
    STOP_LOSS("stopLoss"),
    STOP_LOSS_LIMIT("stopLossLimit"),
    TAKE_PROFIT("takeProfit"),
    TAKE_PROFIT_LIMIT("takeProfitLimit");

    private final String identifier;

    BitvavoOrderType(String identifier) {
        this.identifier = identifier;
    }

    @JsonValue
    public String getIdentifier() {
        return identifier;
    }
}
