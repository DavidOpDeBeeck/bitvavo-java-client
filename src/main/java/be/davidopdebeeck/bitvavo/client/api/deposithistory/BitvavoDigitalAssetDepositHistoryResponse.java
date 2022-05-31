package be.davidopdebeeck.bitvavo.client.api.deposithistory;

import be.davidopdebeeck.bitvavo.client.api.asset.BitvavoAssetSymbol;

import java.math.BigDecimal;
import java.time.Instant;

import static java.util.Objects.requireNonNull;

public class BitvavoDigitalAssetDepositHistoryResponse {

    private Instant timestamp;
    private BitvavoAssetSymbol symbol;
    private BigDecimal amount;
    private String address;
    private String paymentId;
    private String txId;
    private BigDecimal fee;

    private BitvavoDigitalAssetDepositHistoryResponse() {
    }

    private BitvavoDigitalAssetDepositHistoryResponse(Builder builder) {
        timestamp = requireNonNull(builder.timestamp);
        symbol = requireNonNull(builder.symbol);
        amount = requireNonNull(builder.amount);
        address = requireNonNull(builder.address);
        paymentId = requireNonNull(builder.paymentId);
        txId = requireNonNull(builder.txId);
        fee = requireNonNull(builder.fee);
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public BitvavoAssetSymbol getSymbol() {
        return symbol;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getAddress() {
        return address;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getTxId() {
        return txId;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public static final class Builder {

        private Instant timestamp;
        private BitvavoAssetSymbol symbol;
        private BigDecimal amount;
        private String address;
        private String paymentId;
        private String txId;
        private BigDecimal fee;

        public Builder withTimestamp(Instant timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder withSymbol(BitvavoAssetSymbol symbol) {
            this.symbol = symbol;
            return this;
        }

        public Builder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder withAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder withPaymentId(String paymentId) {
            this.paymentId = paymentId;
            return this;
        }

        public Builder withTxId(String txId) {
            this.txId = txId;
            return this;
        }

        public Builder withFee(BigDecimal fee) {
            this.fee = fee;
            return this;
        }

        public BitvavoDigitalAssetDepositHistoryResponse build() {
            return new BitvavoDigitalAssetDepositHistoryResponse(this);
        }
    }
}
