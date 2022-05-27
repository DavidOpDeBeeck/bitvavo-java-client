package be.davidopdebeeck.bitvavo.client.websocket.request;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;

public class BitvavoWebsocketRequest {

    private final String action;
    @JsonUnwrapped
    private final Object request;

    private BitvavoWebsocketRequest(Builder builder) {
        action = requireNonNull(builder.action);
        request = builder.request;
    }

    public String getAction() {
        return action;
    }

    public Optional<Object> getRequest() {
        return ofNullable(request);
    }

    public static final class Builder {

        private String action;
        private Object request;

        public Builder withAction(String action) {
            this.action = action;
            return this;
        }

        public Builder withRequest(Object request) {
            this.request = request;
            return this;
        }

        public BitvavoWebsocketRequest build() {
            return new BitvavoWebsocketRequest(this);
        }
    }


}
