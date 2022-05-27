package be.davidopdebeeck.bitvavo.client.api.authenticate;

import static java.util.Objects.requireNonNull;

public class BitvavoAuthenticateResponse {

    private boolean authenticated;

    private BitvavoAuthenticateResponse() {
    }

    private BitvavoAuthenticateResponse(Builder builder) {
        authenticated = requireNonNull(builder.authenticated);
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public static final class Builder {

        private Boolean authenticated;

        public Builder withAuthenticated(boolean authenticated) {
            this.authenticated = authenticated;
            return this;
        }

        public BitvavoAuthenticateResponse build() {
            return new BitvavoAuthenticateResponse(this);
        }
    }
}
