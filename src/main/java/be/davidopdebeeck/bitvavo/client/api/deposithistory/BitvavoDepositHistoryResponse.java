package be.davidopdebeeck.bitvavo.client.api.deposithistory;

import be.davidopdebeeck.bitvavo.client.api.asset.BitvavoAssetSymbol;

import java.math.BigDecimal;
import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;

public class BitvavoDepositHistoryResponse {

    private long timestamp;
    private BitvavoAssetSymbol symbol;
    private BigDecimal amount;
    private String address;
    private String paymentId;
    private String txId;
    private BigDecimal fee;

    private BitvavoDepositHistoryResponse() {
    }

    private BitvavoDepositHistoryResponse(Builder builder) {
        timestamp = requireNonNull(builder.timestamp);
        symbol = requireNonNull(builder.symbol);
        amount = requireNonNull(builder.amount);
        address = requireNonNull(builder.address);
        paymentId = builder.paymentId;
        txId = builder.txId;
        fee = requireNonNull(builder.fee);
    }

    public long getTimestamp() {
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

    public static final class Builder {

        private Long timestamp;
        private BitvavoAssetSymbol symbol;
        private BigDecimal amount;
        private String address;
        private String paymentId;
        private String txId;
        private BigDecimal fee;

        public Builder withTimestamp(long timestamp) {
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

        public BitvavoDepositHistoryResponse build() {
            return new BitvavoDepositHistoryResponse(this);
        }
    }
}
