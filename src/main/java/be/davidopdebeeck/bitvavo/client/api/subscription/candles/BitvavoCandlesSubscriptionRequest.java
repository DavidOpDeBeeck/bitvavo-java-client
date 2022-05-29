package be.davidopdebeeck.bitvavo.client.api.subscription.candles;

import be.davidopdebeeck.bitvavo.client.api.interval.BitvavoInterval;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class BitvavoCandlesSubscriptionRequest {

    @JsonProperty("interval")
    private final List<BitvavoInterval> intervals;

    private BitvavoCandlesSubscriptionRequest(Builder builder) {
        intervals = requireNonNull(builder.intervals);
    }

    public List<BitvavoInterval> getIntervals() {
        return intervals;
    }

    public static final class Builder {

        private List<BitvavoInterval> intervals;

        public Builder withIntervals(List<BitvavoInterval> intervals) {
            this.intervals = intervals;
            return this;
        }

        public BitvavoCandlesSubscriptionRequest build() {
            return new BitvavoCandlesSubscriptionRequest(this);
        }
    }
}
