package be.davidopdebeeck.bitvavo.client.websocket.handler.event;

import be.davidopdebeeck.bitvavo.client.response.BitvavoResponseHandler;
import be.davidopdebeeck.bitvavo.client.response.BitvavoResponseParser;

import static be.davidopdebeeck.bitvavo.client.response.BitvavoResponseMetadata.emptyMetadata;
import static java.util.Objects.requireNonNull;

public class BitvavoWebsocketEventHandler<T> {

    private final boolean oneTimeUse;
    private final BitvavoResponseParser<T> responseParser;
    private final BitvavoResponseHandler<T> responseHandler;

    private BitvavoWebsocketEventHandler(Builder<T> builder) {
        oneTimeUse = requireNonNull(builder.oneTimeUse);
        responseParser = requireNonNull(builder.responseParser);
        responseHandler = requireNonNull(builder.responseHandler);
    }

    public void handle(String responseAsString) {
        responseHandler.handle(responseParser.parseResponse(responseAsString, emptyMetadata()));
    }

    public boolean isOneTimeUse() {
        return oneTimeUse;
    }

    public static final class Builder<T> {

        private Boolean oneTimeUse;
        private BitvavoResponseParser<T> responseParser;
        private BitvavoResponseHandler<T> responseHandler;

        public Builder<T> withOneTimeUse(boolean oneTimeUse) {
            this.oneTimeUse = oneTimeUse;
            return this;
        }

        public Builder<T> withResponseParser(BitvavoResponseParser<T> responseParser) {
            this.responseParser = responseParser;
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
