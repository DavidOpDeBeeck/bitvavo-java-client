package be.davidopdebeeck.bitvavo.client.api.interval;

import java.util.Arrays;

import static java.lang.String.format;

public enum BitvavoInterval {
    ONE_MINUTE("1m"),
    FIVE_MINUTES("5m"),
    FIFTEEN_MINUTES("15m"),
    THIRTY_MINUTES("30m"),
    ONE_HOUR("1h"),
    TWO_HOURS("2h"),
    FOUR_HOURS("4h"),
    SIX_HOURS("6h"),
    EIGHT_HOURS("8h"),
    TWELVE_HOURS("12h"),
    ONE_DAY("1d");

    private final String identifier;

    BitvavoInterval(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public static BitvavoInterval fromIdentifier(String identifier) {
        return Arrays.stream(values())
            .filter(interval -> interval.identifier.equals(identifier))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(format("Identifier %s is currently not defined in BitvavoInterval", identifier)));
    }
}
