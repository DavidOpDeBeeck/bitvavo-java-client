package be.davidopdebeeck.bitvavo.client.http.request;

import be.davidopdebeeck.bitvavo.client.BitvavoClientException;

import java.net.http.HttpRequest;

import static be.davidopdebeeck.bitvavo.client.utils.SignatureUtils.createSignature;

public class BitvavoHttpGETRequest extends BitvavoHttpRequest {

    private BitvavoHttpGETRequest(Builder builder) {
        super(builder);
    }

    @Override
    protected HttpRequest.Builder httpRequestBuilder() {
        return HttpRequest.newBuilder()
            .GET();
    }

    @Override
    protected String createHeaderSignature(long currentTimeMillis) {
        try {
            // TODO: this is a weird way to determine the path of a uri - toURL().getFile() also includes the query parameters
            return createSignature(uri.toURL().getFile(), "GET", currentTimeMillis, configuration.getApiSecret());
        } catch (Exception e) {
            throw new BitvavoClientException(e);
        }
    }

    public static class Builder extends BitvavoHttpRequest.Builder<Builder> {

        public BitvavoHttpGETRequest build() {
            return new BitvavoHttpGETRequest(this);
        }
    }
}
