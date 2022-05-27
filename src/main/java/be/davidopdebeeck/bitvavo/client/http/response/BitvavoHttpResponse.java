package be.davidopdebeeck.bitvavo.client.http.response;

import be.davidopdebeeck.bitvavo.client.BitvavoClientException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.http.HttpResponse;

import static java.util.Objects.requireNonNull;

public class BitvavoHttpResponse<T> {

    private final ObjectMapper objectMapper;
    private final HttpResponse<String> response;
    private final Class<T> responseTypeClass;

    public BitvavoHttpResponse(Builder<T> builder) {
        this.response = requireNonNull(builder.response);
        this.objectMapper = requireNonNull(builder.objectMapper);
        this.responseTypeClass = requireNonNull(builder.responseTypeClass);
    }

    public String asString() {
        return response.body();
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
        private HttpResponse<String> response;
        private final Class<T> responseTypeClass;

        public Builder(Class<T> responseTypeClass) {
            this.responseTypeClass = responseTypeClass;
        }

        public Builder<T> withObjectMapper(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
            return this;
        }

        public Builder<T> withResponse(HttpResponse<String> response) {
            this.response = response;
            return this;
        }

        public BitvavoHttpResponse<T> build() {
            return new BitvavoHttpResponse<>(this);
        }
    }
}
