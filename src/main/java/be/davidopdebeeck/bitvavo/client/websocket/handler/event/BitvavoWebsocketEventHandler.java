package be.davidopdebeeck.bitvavo.client.websocket.handler.event;

import be.davidopdebeeck.bitvavo.client.response.BitvavoResponse;
import be.davidopdebeeck.bitvavo.client.response.BitvavoResponseHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

import static be.davidopdebeeck.bitvavo.client.response.BitvavoErrorMessage.fromException;
import static be.davidopdebeeck.bitvavo.client.response.BitvavoResponse.error;
import static be.davidopdebeeck.bitvavo.client.response.BitvavoResponse.ok;
import static java.util.Objects.requireNonNull;

public class BitvavoWebsocketEventHandler<T> {

    private final boolean oneTimeUse;
    private final Class<T> responseClass;
    private final ObjectMapper objectMapper;
    private final BitvavoResponseHandler<T> responseHandler;

    private BitvavoWebsocketEventHandler(Builder<T> builder) {
        oneTimeUse = requireNonNull(builder.oneTimeUse);
        responseClass = requireNonNull(builder.responseClass);
        objectMapper = requireNonNull(builder.objectMapper);
        responseHandler = requireNonNull(builder.responseHandler);
    }

    public void handle(Map<String, Object> responseAsMap) {
        responseHandler.handle(createResponse(responseAsMap));
    }

    private BitvavoResponse<T> createResponse(Map<String, Object> responseAsMap) {
        try {
            T response = objectMapper.convertValue(responseAsMap, responseClass);
            return ok(response);
        } catch (Exception exception) {
            return error(fromException(exception));
        }
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
