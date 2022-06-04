package be.davidopdebeeck.bitvavo.client.websocket.handler.event;

import be.davidopdebeeck.bitvavo.client.response.BitvavoResponseHandler;
import be.davidopdebeeck.bitvavo.client.response.BitvavoResponseParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import static java.util.Objects.requireNonNull;

public class BitvavoWebsocketEventHandler<T> {

    private final boolean oneTimeUse;
    private final BitvavoResponseHandler<T> responseHandler;
    private final BitvavoResponseParser<T> responseParser;

    private BitvavoWebsocketEventHandler(Builder<T> builder) {
        oneTimeUse = requireNonNull(builder.oneTimeUse);
        responseHandler = requireNonNull(builder.responseHandler);
        responseParser = new BitvavoResponseParser.Builder<>(builder.responseClass)
            .withObjectMapper(builder.objectMapper)
            .build();
    }

    public void handle(String responseAsString) {
        responseHandler.handle(responseParser.parseResponse(responseAsString));
    }

    public boolean isOneTimeUse() {
        return oneTimeUse;
    }

    public static final class Builder<T> {

        private final Class<T> responseClass;
        private Boolean oneTimeUse;
        private ObjectMapper objectMapper;
        private BitvavoResponseHandler<T> responseHandler;

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

        public Builder<T> withResponseHandler(BitvavoResponseHandler<T> responseHandler) {
            this.responseHandler = responseHandler;
            return this;
        }

        public BitvavoWebsocketEventHandler<T> build() {
            return new BitvavoWebsocketEventHandler<>(this);
        }
    }
}
