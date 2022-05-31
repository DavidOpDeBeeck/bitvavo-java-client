package be.davidopdebeeck.bitvavo.client.api.assets;

import be.davidopdebeeck.bitvavo.client.api.asset.BitvavoAssetSymbol;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class BitvavoAssetsResponse {

    private BitvavoAssetSymbol symbol;
    private String name;
    private long decimals;
    private BigDecimal depositFee;
    private long depositConfirmations;
    private BitvavoAssetStatus depositStatus;
    private BigDecimal withdrawalFee;
    private BigDecimal withdrawalMinAmount;
    private BitvavoAssetStatus withdrawalStatus;
    private List<String> networks;
    private String message;

    private BitvavoAssetsResponse() {
    }

    private BitvavoAssetsResponse(Builder builder) {
        this.symbol = requireNonNull(builder.symbol);
        this.name = requireNonNull(builder.name);
        this.decimals = requireNonNull(builder.decimals);
        this.depositFee = requireNonNull(builder.depositFee);
        this.depositConfirmations = requireNonNull(builder.depositConfirmations);
        this.depositStatus = requireNonNull(builder.depositStatus);
        this.withdrawalFee = requireNonNull(builder.withdrawalFee);
        this.withdrawalMinAmount = requireNonNull(builder.withdrawalMinAmount);
        this.withdrawalStatus = requireNonNull(builder.withdrawalStatus);
        this.networks = requireNonNull(builder.networks);
        this.message = requireNonNull(builder.message);
    }

    public BitvavoAssetSymbol getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public long getDecimals() {
        return decimals;
    }

    public BigDecimal getDepositFee() {
        return depositFee;
    }

    public long getDepositConfirmations() {
        return depositConfirmations;
    }

    public BitvavoAssetStatus getDepositStatus() {
        return depositStatus;
    }

    public BigDecimal getWithdrawalFee() {
        return withdrawalFee;
    }

    public BigDecimal getWithdrawalMinAmount() {
        return withdrawalMinAmount;
    }

    public BitvavoAssetStatus getWithdrawalStatus() {
        return withdrawalStatus;
    }

    public List<String> getNetworks() {
        return networks;
    }

    public String getMessage() {
        return message;
    }

    public static final class Builder {

        private BitvavoAssetSymbol symbol;
        private String name;
        private Long decimals;
        private BigDecimal depositFee;
        private Long depositConfirmations;
        private BitvavoAssetStatus depositStatus;
        private BigDecimal withdrawalFee;
        private BigDecimal withdrawalMinAmount;
        private BitvavoAssetStatus withdrawalStatus;
        private List<String> networks;
        private String message;

        public Builder withSymbol(BitvavoAssetSymbol symbol) {
            this.symbol = symbol;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withDecimals(long decimals) {
            this.decimals = decimals;
            return this;
        }

        public Builder withDepositFee(BigDecimal depositFee) {
            this.depositFee = depositFee;
            return this;
        }

        public Builder withDepositConfirmations(long depositConfirmations) {
            this.depositConfirmations = depositConfirmations;
            return this;
        }

        public Builder withDepositStatus(BitvavoAssetStatus depositStatus) {
            this.depositStatus = depositStatus;
            return this;
        }

        public Builder withWithdrawalFee(BigDecimal withdrawalFee) {
            this.withdrawalFee = withdrawalFee;
            return this;
        }

        public Builder withWithdrawalMinAmount(BigDecimal withdrawalMinAmount) {
            this.withdrawalMinAmount = withdrawalMinAmount;
            return this;
        }

        public Builder withWithdrawalStatus(BitvavoAssetStatus withdrawalStatus) {
            this.withdrawalStatus = withdrawalStatus;
            return this;
        }

        public Builder withNetworks(List<String> networks) {
            this.networks = networks;
            return this;
        }

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public BitvavoAssetsResponse build() {
            return new BitvavoAssetsResponse(this);
        }
    }
}
