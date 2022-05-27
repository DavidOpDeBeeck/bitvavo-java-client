package be.davidopdebeeck.bitvavo.client.api.account;

import static java.util.Objects.requireNonNull;

public class BitvavoAccountResponse {

    private BitvavoFees fees;

    private BitvavoAccountResponse() {
    }

    private BitvavoAccountResponse(Builder builder) {
        fees = requireNonNull(builder.fees);
    }

    public BitvavoFees getFees() {
        return fees;
    }

    public static final class Builder {

        private BitvavoFees fees;

        public Builder withFees(BitvavoFees fees) {
            this.fees = fees;
            return this;
        }

        public BitvavoAccountResponse build() {
            return new BitvavoAccountResponse(this);
        }
    }
}
