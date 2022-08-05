package be.davidopdebeeck.bitvavo.client;

import be.davidopdebeeck.bitvavo.client.ratelimiter.BitvavoRateLimiter;
import be.davidopdebeeck.bitvavo.client.ratelimiter.BitvavoServerSideRateLimiter;
import com.fasterxml.jackson.databind.ObjectMapper;

import static be.davidopdebeeck.bitvavo.client.serialization.BitvavoSerializationModule.bitvavoObjectMapper;
import static java.util.Objects.requireNonNull;

public class BitvavoClientConfiguration {

    private final String apiKey;
    private final String apiSecret;
    private final String restUrl;
    private final String wsUrl;
    private final int accessWindow;
    private final ObjectMapper objectMapper;
    private final BitvavoRateLimiter rateLimiter;

    public BitvavoClientConfiguration(Builder builder) {
        this.apiKey = requireNonNull(builder.apiKey);
        this.apiSecret = requireNonNull(builder.apiSecret);
        this.restUrl = requireNonNull(builder.restUrl);
        this.wsUrl = requireNonNull(builder.wsUrl);
        this.accessWindow = requireNonNull(builder.accessWindow);
        this.objectMapper = bitvavoObjectMapper();
        this.rateLimiter = requireNonNull(builder.rateLimiter);
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public String getRestUrl() {
        return restUrl;
    }

    public String getWsUrl() {
        return wsUrl;
    }

    public int getAccessWindow() {
        return accessWindow;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public BitvavoRateLimiter getRateLimiter() {
        return rateLimiter;
    }

    public static final class Builder {

        private String apiKey;
        private String apiSecret;
        private String restUrl;
        private String wsUrl;
        private Integer accessWindow;
        private BitvavoRateLimiter rateLimiter = new BitvavoServerSideRateLimiter();

        public Builder withApiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        public Builder withApiSecret(String apiSecret) {
            this.apiSecret = apiSecret;
            return this;
        }

        public Builder withRestUrl(String restUrl) {
            this.restUrl = restUrl;
            return this;
        }

        public Builder withWsUrl(String wsUrl) {
            this.wsUrl = wsUrl;
            return this;
        }

        public Builder withAccessWindow(int accessWindow) {
            this.accessWindow = accessWindow;
            return this;
        }

        public Builder withRateLimiter(BitvavoRateLimiter rateLimiter) {
            this.rateLimiter = rateLimiter;
            return this;
        }

        public BitvavoClientConfiguration build() {
            return new BitvavoClientConfiguration(this);
        }
    }
}
