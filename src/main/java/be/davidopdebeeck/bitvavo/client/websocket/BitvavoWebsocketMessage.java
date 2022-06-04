package be.davidopdebeeck.bitvavo.client.websocket;

import static java.util.Objects.requireNonNull;

public class BitvavoWebsocketMessage {

    private final String identifier;
    private final String body;

    private BitvavoWebsocketMessage(Builder builder) {
        identifier = requireNonNull(builder.identifier);
        body = requireNonNull(builder.body);
    }

    public String getMessageIdentifier() {
        return identifier;
    }

    public String getMessageBody() {
        return body;
    }

    public static final class Builder {

        private String identifier;
        private String body;

        public Builder withIdentifier(String identifier) {
            this.identifier = identifier;
            return this;
        }

        public Builder withBody(String body) {
            this.body = body;
            return this;
        }

        public BitvavoWebsocketMessage build() {
            return new BitvavoWebsocketMessage(this);
        }
    }
}
