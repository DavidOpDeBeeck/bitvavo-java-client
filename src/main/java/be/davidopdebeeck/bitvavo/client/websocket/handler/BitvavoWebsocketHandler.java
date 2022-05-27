package be.davidopdebeeck.bitvavo.client.websocket.handler;

import be.davidopdebeeck.bitvavo.client.websocket.response.BitvavoWebsocketResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import static java.util.Objects.requireNonNull;

public class BitvavoWebsocketHandler<T> {

    private final boolean oneTimeUse;
    private final Class<T> responseClass;
    private final ObjectMapper objectMapper;
    private final BitvavoWebsocketResponseHandler<T> responseHandler;

    private BitvavoWebsocketHandler(Builder<T> builder) {
        oneTimeUse = requireNonNull(builder.oneTimeUse);
        responseClass = requireNonNull(builder.responseClass);
        objectMapper = requireNonNull(builder.objectMapper);
        responseHandler = requireNonNull(builder.responseHandler);
    }

    public void handle(String response) {
        responseHandler.handle(new BitvavoWebsocketResponse.Builder<>(responseClass)
            .withResponse(response)
            .withObjectMapper(objectMapper)
            .build());
    }

    public boolean isOneTimeUse() {
        return oneTimeUse;
    }

    public static final class Builder<T> {

        private final Class<T> responseClass;
        private Boolean oneTimeUse;
        private ObjectMapper objectMapper;
        private BitvavoWebsocketResponseHandler<T> responseHandler;

        public Builder(Class<T> responseClass) {
            this.responseClass = responseClass;
        }

        public Builder<T> withOneTimeUse(boolean oneTimeUse) {
            this.oneTimeUse = oneTimeUse;
            return this;
        }

        public Builder<T> withObjectMapper(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
            return this;
        }

        public Builder<T> withResponseHandler(BitvavoWebsocketResponseHandler<T> responseHandler) {
            this.responseHandler = responseHandler;
            return this;
        }

        public BitvavoWebsocketHandler<T> build() {
            return new BitvavoWebsocketHandler<>(this);
        }
    }
}
