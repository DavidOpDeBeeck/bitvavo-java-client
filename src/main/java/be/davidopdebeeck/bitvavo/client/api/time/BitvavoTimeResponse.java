package be.davidopdebeeck.bitvavo.client.api.time;

import java.time.Instant;

import static java.util.Objects.requireNonNull;

public class BitvavoTimeResponse {

    private Instant time;

    private BitvavoTimeResponse() {
    }

    private BitvavoTimeResponse(Builder builder) {
        time = requireNonNull(builder.time);
    }

    public Instant getTime() {
        return time;
    }

    public static final class Builder {

        private Instant time;

        public Builder withTime(Instant time) {
            this.time = time;
            return this;
        }

        public BitvavoTimeResponse build() {
            return new BitvavoTimeResponse(this);
        }
    }
}
