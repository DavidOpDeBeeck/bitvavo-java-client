package be.davidopdebeeck.bitvavo.client.response;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

import static be.davidopdebeeck.bitvavo.client.response.BitvavoErrorMessage.fromException;
import static be.davidopdebeeck.bitvavo.client.response.BitvavoResponse.error;
import static be.davidopdebeeck.bitvavo.client.response.BitvavoResponse.ok;
import static java.util.Objects.requireNonNull;

public class BitvavoResponseParser<T> {

    private static final String ERROR_CODE_FIELD_NAME = "errorCode";
    private static final String ERROR_MESSAGE_FIELD_NAME = "error";

    private final ObjectMapper objectMapper;
    private final Class<T> responseTypeClass;

    private BitvavoResponseParser(Builder<T> builder) {
        objectMapper = requireNonNull(builder.objectMapper);
        responseTypeClass = requireNonNull(builder.responseTypeClass);
    }

    public BitvavoResponse<T> parseResponse(String responseAsString) {
        try {
            JsonNode responseNode = objectMapper.readTree(responseAsString);
            if (responseNode.has(ERROR_CODE_FIELD_NAME)) {
                return error(new BitvavoErrorMessage.Builder()
                    .withErrorCode(responseNode.get(ERROR_CODE_FIELD_NAME).asText())
                    .withErrorMessage(responseNode.get(ERROR_MESSAGE_FIELD_NAME).asText())
                    .build());
            }
            return ok(objectMapper.convertValue(responseNode, responseTypeClass));
        } catch (Exception exception) {
            return error(fromException(exception));
        }
    }

    public BitvavoResponse<T> createWebsocketResponse(Map<String, Object> responseAsMap) {
        try {
            return ok(objectMapper.convertValue(responseAsMap, responseTypeClass));
        } catch (Exception exception) {
            return error(fromException(exception));
        }
    }

    public static final class Builder<T> {

        private ObjectMapper objectMapper;
        private final Class<T> responseTypeClass;

        public Builder(Class<T> responseTypeClass) {
            this.responseTypeClass = responseTypeClass;
        }

        public Builder<T> withObjectMapper(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
            return this;
        }

        public BitvavoResponseParser<T> build() {
            return new BitvavoResponseParser<>(this);
        }
    }
}
