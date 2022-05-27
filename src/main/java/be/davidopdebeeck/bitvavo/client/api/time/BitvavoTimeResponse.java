package be.davidopdebeeck.bitvavo.client.api.time;

import static java.util.Objects.requireNonNull;

public class BitvavoTimeResponse {

    private long time;

    private BitvavoTimeResponse() {
    }

    private BitvavoTimeResponse(Builder builder) {
        time = requireNonNull(builder.time);
    }

    public long getTime() {
        return time;
    }

    public static final class Builder {

        private Long time;

        public Builder withTime(long time) {
            this.time = time;
            return this;
        }

        public BitvavoTimeResponse build() {
            return new BitvavoTimeResponse(this);
        }
    }
}
