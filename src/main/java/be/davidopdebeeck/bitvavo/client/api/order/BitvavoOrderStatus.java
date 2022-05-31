package be.davidopdebeeck.bitvavo.client.api.order;

import com.fasterxml.jackson.annotation.JsonValue;

public enum BitvavoOrderStatus {
    NEW("new"),
    AWAITING_TRIGGER("awaitingTrigger"),
    CANCELED("canceled"),
    CANCELED_AUCTION("canceledAuction"),
    CANCELED_SELF_TRADE_PREVENTION("canceledSelfTradePrevention"),
    CANCELED_IOC("canceledIOC"),
    CANCELED_FOK("canceledFOK"),
    CANCELED_MARKET_PROTECTION("canceledMarketProtection"),
    CANCELED_POST_ONLY("canceledPostOnly"),
    FILLED("filled"),
    PARTIALLY_FILLED("partiallyFilled"),
    EXPIRED("expired"),
    REJECTED("rejected");

    private final String identifier;

    BitvavoOrderStatus(String identifier) {
        this.identifier = identifier;
    }

    @JsonValue
    public String getIdentifier() {
        return identifier;
    }
}
