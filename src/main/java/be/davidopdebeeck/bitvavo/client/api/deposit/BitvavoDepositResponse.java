package be.davidopdebeeck.bitvavo.client.api.deposit;

import java.util.Optional;

import static java.util.Optional.ofNullable;

public class BitvavoDepositResponse {

    private String address;
    private String paymentId;
    private String iban;
    private String bic;
    private String description;

    private BitvavoDepositResponse() {
    }

    private BitvavoDepositResponse(Builder builder) {
        address = builder.address;
        paymentId = builder.paymentId;
        iban = builder.iban;
        bic = builder.bic;
        description = builder.description;
    }

    public Optional<String> getAddress() {
        return ofNullable(address);
    }

    public Optional<String> getPaymentId() {
        return ofNullable(paymentId);
    }

    public Optional<String> getIban() {
        return ofNullable(iban);
    }

    public Optional<String> getBic() {
        return ofNullable(bic);
    }

    public Optional<String> getDescription() {
        return ofNullable(description);
    }

    public static final class Builder {

        private String address;
        private String paymentId;
        private String iban;
        private String bic;
        private String description;

        public Builder withAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder withPaymentId(String paymentId) {
            this.paymentId = paymentId;
            return this;
        }

        public Builder withIban(String iban) {
            this.iban = iban;
            return this;
        }

        public Builder withBic(String bic) {
            this.bic = bic;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public BitvavoDepositResponse build() {
            return new BitvavoDepositResponse(this);
        }
    }
}
