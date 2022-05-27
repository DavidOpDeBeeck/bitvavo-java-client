package be.davidopdebeeck.bitvavo.client.api.deposithistory;

import be.davidopdebeeck.bitvavo.client.api.asset.BitvavoAssetSymbol;

import static java.util.Objects.requireNonNull;

public class BitvavoBankDepositHistoryResponse {

    private long timestamp;
    private BitvavoAssetSymbol symbol;
    private String amount;
    private String address;
    private String fee;

    private BitvavoBankDepositHistoryResponse() {
    }

    private BitvavoBankDepositHistoryResponse(Builder builder) {
        timestamp = requireNonNull(builder.timestamp);
        symbol = requireNonNull(builder.symbol);
        amount = requireNonNull(builder.amount);
        address = requireNonNull(builder.address);
        fee = requireNonNull(builder.fee);
    }

    public long getTimestamp() {
        return timestamp;
    }

    public BitvavoAssetSymbol getSymbol() {
        return symbol;
    }

    public String getAmount() {
        return amount;
    }

    public String getAddress() {
        return address;
    }

    public String getFee() {
        return fee;
    }

    public static final class Builder {

        private Long timestamp;
        private BitvavoAssetSymbol symbol;
        private String amount;
        private String address;
        private String fee;

        public Builder withTimestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder withSymbol(BitvavoAssetSymbol symbol) {
            this.symbol = symbol;
            return this;
        }

        public Builder withAmount(String amount) {
            this.amount = amount;
            return this;
        }

        public Builder withAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder withFee(String fee) {
            this.fee = fee;
            return this;
        }

        public BitvavoBankDepositHistoryResponse build() {
            return new BitvavoBankDepositHistoryResponse(this);
        }
    }
}
