package be.davidopdebeeck.bitvavo.client.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import static be.davidopdebeeck.bitvavo.client.response.BitvavoErrorMessage.fromException;
import static be.davidopdebeeck.bitvavo.client.response.BitvavoResponse.error;
import static java.util.Objects.requireNonNull;

public class BitvavoResponseParser<T> {

    private final JavaType responseType;
    private final ObjectMapper objectMapper;

    private BitvavoResponseParser(Builder<T> builder) {
        this.objectMapper = requireNonNull(builder.objectMapper);
        this.responseType = createResponseType(builder);
    }

    public BitvavoResponse<T> parseResponse(String responseAsString) {
        try {
            return objectMapper.readValue(responseAsString, responseType);
        } catch (JsonProcessingException exception) {
            return error(fromException(exception));
        }
    }

    private JavaType createResponseType(Builder<T> builder) {
        return builder.objectMapper.getTypeFactory()
            .constructParametricType(BitvavoResponse.class, builder.responseTypeClass);
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
