package be.davidopdebeeck.bitvavo.client.api.withdrawalhistory;

import be.davidopdebeeck.bitvavo.client.api.asset.BitvavoAssetSymbol;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;

public class BitvavoWithdrawalHistoryResponse {

    private Instant timestamp;
    private BitvavoAssetSymbol symbol;
    private BigDecimal amount;
    private String address;
    private String paymentId;
    private String txId;
    private BigDecimal fee;
    private String status;

    private BitvavoWithdrawalHistoryResponse() {
    }

    private BitvavoWithdrawalHistoryResponse(Builder builder) {
        timestamp = requireNonNull(builder.timestamp);
        symbol = requireNonNull(builder.symbol);
        amount = requireNonNull(builder.amount);
        address = requireNonNull(builder.address);
        paymentId = builder.paymentId;
        txId = builder.txId;
        fee = requireNonNull(builder.fee);
        status = requireNonNull(builder.status);
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

    public Optional<String> getPaymentId() {
        return ofNullable(paymentId);
    }

    public Optional<String> getTxId() {
        return ofNullable(txId);
    }

    public BigDecimal getFee() {
        return fee;
    }

    public String getStatus() {
        return status;
    }

    public static final class Builder {

        private Instant timestamp;
        private BitvavoAssetSymbol symbol;
        private BigDecimal amount;
        private String address;
        private String paymentId;
        private String txId;
        private BigDecimal fee;
        private String status;

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

        public Builder withStatus(String status) {
            this.status = status;
            return this;
        }

        public BitvavoWithdrawalHistoryResponse build() {
            return new BitvavoWithdrawalHistoryResponse(this);
        }
    }
}
