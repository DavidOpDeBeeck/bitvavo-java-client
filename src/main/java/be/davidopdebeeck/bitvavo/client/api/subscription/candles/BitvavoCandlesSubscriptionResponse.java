package be.davidopdebeeck.bitvavo.client.api.subscription.candles;

import be.davidopdebeeck.bitvavo.client.api.interval.BitvavoInterval;
import be.davidopdebeeck.bitvavo.client.api.market.BitvavoMarket;
import be.davidopdebeeck.bitvavo.client.api.market.candles.BitvavoCandle;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class BitvavoCandlesSubscriptionResponse {

    private BitvavoMarket market;
    private BitvavoInterval interval;
    @JsonProperty("candle")
    private List<BitvavoCandle> candles;

    private BitvavoCandlesSubscriptionResponse() {
    }

    private BitvavoCandlesSubscriptionResponse(Builder builder) {
        market = requireNonNull(builder.market);
        interval = requireNonNull(builder.interval);
        candles = requireNonNull(builder.candles);
    }

    public BitvavoMarket getMarket() {
        return market;
    }

    public BitvavoInterval getInterval() {
        return interval;
    }

    public List<BitvavoCandle> getCandles() {
        return candles;
    }

    public static final class Builder {

        private BitvavoMarket market;
        private BitvavoInterval interval;
        private List<BitvavoCandle> candles;

        public Builder withMarket(BitvavoMarket market) {
            this.market = market;
            return this;
        }

        public Builder withInterval(BitvavoInterval interval) {
            this.interval = interval;
            return this;
        }

        public Builder withCandles(List<BitvavoCandle> candles) {
            this.candles = candles;
            return this;
        }

        public BitvavoCandlesSubscriptionResponse build() {
            return new BitvavoCandlesSubscriptionResponse(this);
        }
    }
}
