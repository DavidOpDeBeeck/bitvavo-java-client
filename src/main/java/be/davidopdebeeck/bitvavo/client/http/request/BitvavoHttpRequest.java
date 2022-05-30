package be.davidopdebeeck.bitvavo.client.http.request;

import be.davidopdebeeck.bitvavo.client.BitvavoClientConfiguration;

import java.net.URI;
import java.net.http.HttpRequest;

import static java.lang.String.valueOf;
import static java.util.Objects.requireNonNull;

public abstract class BitvavoHttpRequest {

    protected final URI uri;
    protected final BitvavoClientConfiguration configuration;

    protected BitvavoHttpRequest(Builder<?> builder) {
        this.uri = requireNonNull(builder.uri);
        this.configuration = requireNonNull(builder.configuration);
    }

    protected abstract HttpRequest.Builder httpRequestBuilder();

    protected abstract String createHeaderSignature(long currentTimeMillis);

    public HttpRequest asHttpRequest() {
        long currentTimeMillis = System.currentTimeMillis();

        return httpRequestBuilder()
            .uri(uri)
            .header("BITVAVO-ACCESS-KEY", configuration.getApiKey())
            .header("BITVAVO-ACCESS-SIGNATURE", createHeaderSignature(currentTimeMillis))
            .header("BITVAVO-ACCESS-TIMESTAMP", valueOf(currentTimeMillis))
            .header("BITVAVO-ACCESS-WINDOW", valueOf(configuration.getAccessWindow()))
            .build();
    }

    @SuppressWarnings("unchecked")
    public abstract static class Builder<B extends Builder<B>> {

        private URI uri;
        private BitvavoClientConfiguration configuration;

        public B withUri(URI uri) {
            this.uri = uri;
            return (B) this;
        }

        public B withConfiguration(BitvavoClientConfiguration configuration) {
            this.configuration = configuration;
            return (B) this;
        }
    }
}
