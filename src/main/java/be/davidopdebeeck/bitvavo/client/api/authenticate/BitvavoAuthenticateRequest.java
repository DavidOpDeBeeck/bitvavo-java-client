package be.davidopdebeeck.bitvavo.client.api.authenticate;

import static java.util.Objects.requireNonNull;

public class BitvavoAuthenticateRequest {

    private final String key;
    private final String signature;
    private final String timestamp;
    private final String window;

    private BitvavoAuthenticateRequest(Builder builder) {
        key = requireNonNull(builder.key);
        signature = requireNonNull(builder.signature);
        timestamp = requireNonNull(builder.timestamp);
        window = requireNonNull(builder.window);
    }

    public String getKey() {
        return key;
    }

    public String getSignature() {
        return signature;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getWindow() {
        return window;
    }

    public static final class Builder {

        private String key;
        private String signature;
        private String timestamp;
        private String window;

        public Builder withKey(String key) {
            this.key = key;
            return this;
        }

        public Builder withSignature(String signature) {
            this.signature = signature;
            return this;
        }

        public Builder withTimestamp(String timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder withWindow(String window) {
            this.window = window;
            return this;
        }

        public BitvavoAuthenticateRequest build() {
            return new BitvavoAuthenticateRequest(this);
        }
    }
}
