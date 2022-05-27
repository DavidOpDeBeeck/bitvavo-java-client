package be.davidopdebeeck.bitvavo.client.websocket.response;

import be.davidopdebeeck.bitvavo.client.BitvavoClientException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static java.util.Objects.requireNonNull;

public class BitvavoWebsocketResponse<T> {

    private final ObjectMapper objectMapper;
    private final String response;
    private final Class<T> responseTypeClass;

    public BitvavoWebsocketResponse(Builder<T> builder) {
        this.response = requireNonNull(builder.response);
        this.objectMapper = requireNonNull(builder.objectMapper);
        this.responseTypeClass = requireNonNull(builder.responseTypeClass);
    }

    public String asString() {
        return response;
    }

    public T asType() {
        try {
            return objectMapper.readValue(asString(), responseTypeClass);
        } catch (Exception e) {
            throw new BitvavoClientException(e);
        }
    }

    public static final class Builder<T> {

        private ObjectMapper objectMapper;
        private String response;
        private final Class<T> responseTypeClass;

        public Builder(Class<T> responseTypeClass) {
            this.responseTypeClass = responseTypeClass;
        }

        public Builder<T> withObjectMapper(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
            return this;
        }

        public Builder<T> withResponse(String response) {
            this.response = response;
            return this;
        }

        public BitvavoWebsocketResponse<T> build() {
            return new BitvavoWebsocketResponse<>(this);
        }
    }
}
