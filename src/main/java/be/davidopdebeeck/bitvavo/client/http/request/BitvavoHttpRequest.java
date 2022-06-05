package be.davidopdebeeck.bitvavo.client.http.request;

import be.davidopdebeeck.bitvavo.client.BitvavoClientConfiguration;
import be.davidopdebeeck.bitvavo.client.BitvavoClientException;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.net.URI;
import java.net.http.HttpRequest;

import static be.davidopdebeeck.bitvavo.client.utils.SignatureUtils.createSignature;
import static java.lang.String.format;
import static java.lang.String.valueOf;
import static java.net.http.HttpRequest.BodyPublishers.ofString;
import static java.util.Objects.requireNonNull;

public class BitvavoHttpRequest {

    private final URI uri;
    private final String method;
    private final String bodyAsString;
    private final BitvavoClientConfiguration configuration;

    private BitvavoHttpRequest(Builder builder) {
        this.uri = requireNonNull(builder.uri);
        this.method = requireNonNull(builder.method);
        this.bodyAsString = convertBodyToString(builder.body);
        this.configuration = requireNonNull(builder.configuration);
    }

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

    private HttpRequest.Builder httpRequestBuilder() {
        switch (method) {
            case "GET":
                return HttpRequest.newBuilder()
                    .GET();
            case "POST":
                return HttpRequest.newBuilder()
                    .POST(ofString(bodyAsString))
                    .header("Content-Type", "application/json");
            case "PUT":
                return HttpRequest.newBuilder()
                    .PUT(ofString(bodyAsString))
                    .header("Content-Type", "application/json");
            case "DELETE":
                return HttpRequest.newBuilder()
                    .DELETE();
            default:
                throw new IllegalArgumentException(format("Method %s is not supported in BitvavoHttpRequest", method));
        }
    }

    // TODO: this is a weird way to determine the path of a uri - toURL().getFile() also includes the query parameters
    private String createHeaderSignature(long currentTimeMillis) {
        try {
            if (bodyAsString == null) {
                return createSignature(uri.toURL().getFile(), method, currentTimeMillis, configuration.getApiSecret());
            }
            return createSignature(uri.toURL().getFile(), method, bodyAsString, currentTimeMillis, configuration.getApiSecret());
        } catch (Exception e) {
            throw new BitvavoClientException(e);
        }
    }

    private String convertBodyToString(Object body) {
        try {
            if (body == null) {
                return null;
            }
            return configuration.getObjectMapper().writeValueAsString(body);
        } catch (JsonProcessingException e) {
            throw new BitvavoClientException(e);
        }
    }

    public static class Builder {

        private URI uri;
        private String method;
        private Object body;
        private BitvavoClientConfiguration configuration;

        public Builder withUri(URI uri) {
            this.uri = uri;
            return this;
        }

        public Builder withMethod(String method) {
            this.method = method;
            return this;
        }

        public Builder withBody(Object body) {
            this.body = body;
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
