package be.davidopdebeeck.bitvavo.client.api.withdrawalhistory;

import be.davidopdebeeck.bitvavo.client.api.asset.BitvavoAssetSymbol;

import java.util.Optional;

import static java.util.Optional.ofNullable;

public class BitvavoWithdrawalHistoryRequest {

    private final BitvavoAssetSymbol symbol;
    private final Integer limit;
    private final Long start;
    private final Long end;

    private BitvavoWithdrawalHistoryRequest(Builder builder) {
        symbol = builder.symbol;
        limit = builder.limit;
        start = builder.start;
        end = builder.end;
    }

    public Optional<BitvavoAssetSymbol> getSymbol() {
        return ofNullable(symbol);
    }

    public Optional<Integer> getLimit() {
        return ofNullable(limit);
    }

    public Optional<Long> getStart() {
        return ofNullable(start);
    }

    public Optional<Long> getEnd() {
        return ofNullable(end);
    }

    public static final class Builder {

        private BitvavoAssetSymbol symbol;
        private Integer limit;
        private Long start;
        private Long end;

        public Builder withSymbol(BitvavoAssetSymbol symbol) {
            this.symbol = symbol;
            return this;
        }

        public Builder withLimit(Integer limit) {
            this.limit = limit;
            return this;
        }

        public Builder withStart(Long start) {
            this.start = start;
            return this;
        }

        public Builder withEnd(Long end) {
            this.end = end;
            return this;
        }

        public BitvavoWithdrawalHistoryRequest build() {
            return new BitvavoWithdrawalHistoryRequest(this);
        }
    }
}
