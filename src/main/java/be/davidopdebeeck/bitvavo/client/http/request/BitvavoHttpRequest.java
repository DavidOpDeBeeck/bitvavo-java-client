package be.davidopdebeeck.bitvavo.client.http.request;

import be.davidopdebeeck.bitvavo.client.BitvavoClientConfiguration;
import be.davidopdebeeck.bitvavo.client.BitvavoClientException;
import be.davidopdebeeck.bitvavo.client.utils.SignatureUtils;

import java.net.URI;
import java.net.http.HttpRequest;

import static java.lang.String.valueOf;
import static java.util.Objects.requireNonNull;

public class BitvavoHttpRequest {

    private final URI uri;
    private final String method;
    private final BitvavoClientConfiguration configuration;

    private BitvavoHttpRequest(Builder builder) {
        this.uri = requireNonNull(builder.uri);
        this.method = requireNonNull(builder.method);
        this.configuration = requireNonNull(builder.configuration);
    }

    public HttpRequest asHttpRequest() {
        long currentTimeMillis = System.currentTimeMillis();

        return HttpRequest.newBuilder()
            .uri(uri)
            .header("BITVAVO-ACCESS-KEY", configuration.getApiKey())
            .header("BITVAVO-ACCESS-SIGNATURE", createSignature(currentTimeMillis))
            .header("BITVAVO-ACCESS-TIMESTAMP", valueOf(currentTimeMillis))
            .header("BITVAVO-ACCESS-WINDOW", valueOf(configuration.getAccessWindow()))
            .build();
    }

    private String createSignature(long currentTimeMillis) {
        try {
            // TODO: this is a weird way to determine the path of a uri - toURL().getFile() also includes the query parameters
            return SignatureUtils.createSignature(uri.toURL().getFile(), method, currentTimeMillis, configuration.getApiSecret());
        } catch (Exception e) {
            throw new BitvavoClientException(e);
        }
    }

    public static final class Builder {

        private URI uri;
        private String method;
        private BitvavoClientConfiguration configuration;

        public Builder withUri(URI uri) {
            this.uri = uri;
            return this;
        }

        public Builder withMethod(String method) {
            this.method = method;
            return this;
        }

        public Builder withConfiguration(BitvavoClientConfiguration configuration) {
            this.configuration = configuration;
            return this;
        }

        public BitvavoHttpRequest build() {
            return new BitvavoHttpRequest(this);
        }
    }
}
