package be.davidopdebeeck.bitvavo.client.response;


import java.net.http.HttpHeaders;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Collections.emptyMap;
import static java.util.Objects.requireNonNull;

public class BitvavoResponseMetadata {

    public static BitvavoResponseMetadata emptyMetadata() {
        return new BitvavoResponseMetadata.Builder()
            .withMetadata(emptyMap())
            .build();
    }

    public static BitvavoResponseMetadata metadata(HttpHeaders headers) {
        return new BitvavoResponseMetadata.Builder()
            .withMetadata(headers.map())
            .build();
    }

    private final Map<String, ?> metadata;

    private BitvavoResponseMetadata(Builder builder) {
        metadata = requireNonNull(builder.metadata);
    }

    @SuppressWarnings("unchecked")
    public <T> Optional<List<T>> findValues(String key, Class<T> type) {
        return Optional.ofNullable(metadata.get(key))
            .map(value -> (List<T>) value);
    }

    public static final class Builder {

        private Map<String, ?> metadata;

        public Builder withMetadata(Map<String, ?> metadata) {
            this.metadata = metadata;
            return this;
        }

        public BitvavoResponseMetadata build() {
            return new BitvavoResponseMetadata(this);
        }
    }
}
