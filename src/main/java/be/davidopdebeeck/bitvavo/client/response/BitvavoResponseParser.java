package be.davidopdebeeck.bitvavo.client.response;

import be.davidopdebeeck.bitvavo.client.ratelimiter.BitvavoRateLimiter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.http.HttpHeaders;
import java.net.http.HttpResponse;

import static be.davidopdebeeck.bitvavo.client.response.BitvavoErrorMessage.fromException;
import static be.davidopdebeeck.bitvavo.client.response.BitvavoResponse.error;
import static java.util.Objects.requireNonNull;

public class BitvavoResponseParser<T> {

    private final JavaType responseType;
    private final ObjectMapper objectMapper;
    private final BitvavoRateLimiter rateLimiter;

    private BitvavoResponseParser(Builder<T> builder) {
        this.objectMapper = requireNonNull(builder.objectMapper);
        this.responseType = createResponseType(builder);
        this.rateLimiter = requireNonNull(builder.rateLimiter);
    }

    public BitvavoResponse<T> parseHttpResponse(HttpResponse<String> httpResponse) {
        updateRateLimiter(httpResponse);
        return parseResponse(httpResponse.body());
    }

    public BitvavoResponse<T> parseResponse(String responseAsString) {
        try {
            return objectMapper.<BitvavoResponse<T>>readValue(responseAsString, responseType)
                .onError(rateLimiter::handlePossibleRateLimitError);
        } catch (JsonProcessingException exception) {
            return error(fromException(exception));
        }
    }

    private void updateRateLimiter(HttpResponse<String> httpResponse) {
        HttpHeaders headers = httpResponse.headers();
        headers.firstValueAsLong("Bitvavo-Ratelimit-ResetAt").ifPresent(rateLimiter::updateResetAt);
        headers.firstValueAsLong("Bitvavo-Ratelimit-Remaining").ifPresent(rateLimiter::updateWeightRemaining);
    }

    private JavaType createResponseType(Builder<T> builder) {
        return builder.objectMapper.getTypeFactory()
            .constructParametricType(BitvavoResponse.class, builder.responseTypeClass);
    }

    public static final class Builder<T> {

        private ObjectMapper objectMapper;
        private BitvavoRateLimiter rateLimiter;
        private final Class<T> responseTypeClass;

        public Builder(Class<T> responseTypeClass) {
            this.responseTypeClass = responseTypeClass;
        }

        public Builder<T> withObjectMapper(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
            return this;
        }

        public Builder<T> withRateLimiter(BitvavoRateLimiter rateLimiter) {
            this.rateLimiter = rateLimiter;
            return this;
        }

        public BitvavoResponseParser<T> build() {
            return new BitvavoResponseParser<>(this);
        }
    }
}
