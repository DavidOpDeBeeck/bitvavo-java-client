package be.davidopdebeeck.bitvavo.client.api.market.candles;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class BitvavoMarketCandlesResponse {

    private List<BitvavoCandle> candles;

    private BitvavoMarketCandlesResponse() {
    }

    private BitvavoMarketCandlesResponse(Builder builder) {
        candles = requireNonNull(builder.candles);
    }

    public List<BitvavoCandle> getCandles() {
        return candles;
    }

    public static final class Builder {

        private List<BitvavoCandle> candles;

        public Builder withCandles(List<BitvavoCandle> candles) {
            this.candles = candles;
            return this;
        }

        public BitvavoMarketCandlesResponse build() {
            return new BitvavoMarketCandlesResponse(this);
        }
    }
}
