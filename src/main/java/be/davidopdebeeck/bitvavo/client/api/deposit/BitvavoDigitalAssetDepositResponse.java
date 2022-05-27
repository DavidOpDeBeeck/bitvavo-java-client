package be.davidopdebeeck.bitvavo.client.api.deposit;

import static java.util.Objects.requireNonNull;

public class BitvavoDigitalAssetDepositResponse {

    private String address;
    private String paymentId;

    private BitvavoDigitalAssetDepositResponse() {
    }

    private BitvavoDigitalAssetDepositResponse(Builder builder) {
        address = requireNonNull(builder.address);
        paymentId = requireNonNull(builder.paymentId);
    }

    public String getAddress() {
        return address;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public static final class Builder {

        private String address;
        private String paymentId;

        public Builder withAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder withPaymentId(String paymentId) {
            this.paymentId = paymentId;
            return this;
        }

        public BitvavoDigitalAssetDepositResponse build() {
            return new BitvavoDigitalAssetDepositResponse(this);
        }
    }
}
