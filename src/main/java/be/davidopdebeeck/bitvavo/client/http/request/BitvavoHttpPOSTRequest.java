package be.davidopdebeeck.bitvavo.client.http.request;

import be.davidopdebeeck.bitvavo.client.BitvavoClientException;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.net.http.HttpRequest;

import static be.davidopdebeeck.bitvavo.client.utils.SignatureUtils.createSignature;
import static java.net.http.HttpRequest.BodyPublishers.ofString;
import static java.util.Objects.requireNonNull;

public class BitvavoHttpPOSTRequest extends BitvavoHttpRequest {

    private final String bodyAsString;

    private BitvavoHttpPOSTRequest(Builder builder) {
        super(builder);
        this.bodyAsString = convertBodyToString(requireNonNull(builder.body));
    }

    @Override
    protected HttpRequest.Builder httpRequestBuilder() {
        return HttpRequest.newBuilder()
            .POST(ofString(bodyAsString))
            .header("Content-Type", "application/json");
    }

    @Override
    protected String createHeaderSignature(long currentTimeMillis) {
        try {
            // TODO: this is a weird way to determine the path of a uri - toURL().getFile() also includes the query parameters
            return createSignature(uri.toURL().getFile(), "POST", bodyAsString, currentTimeMillis, configuration.getApiSecret());
        } catch (Exception e) {
            throw new BitvavoClientException(e);
        }
    }

    private String convertBodyToString(Object body) {
        try {
            return configuration.getObjectMapper().writeValueAsString(body);
        } catch (JsonProcessingException e) {
            throw new BitvavoClientException(e);
        }
    }

    public static class Builder extends BitvavoHttpRequest.Builder<Builder> {

        private Object body;

        public Builder withBody(Object body) {
            this.body = body;
            return this;
        }

        public BitvavoHttpPOSTRequest build() {
            return new BitvavoHttpPOSTRequest(this);
        }
    }
}
