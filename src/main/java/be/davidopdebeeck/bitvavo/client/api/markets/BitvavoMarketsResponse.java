package be.davidopdebeeck.bitvavo.client.api.markets;

import be.davidopdebeeck.bitvavo.client.api.market.BitvavoMarket;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class BitvavoMarketsResponse {

    private BitvavoMarket market;
    private String status;
    private String base;
    private String quote;
    private BigDecimal pricePrecision;
    private BigDecimal minOrderInQuoteAsset;
    private BigDecimal minOrderInBaseAsset;
    private List<String> orderTypes;

    private BitvavoMarketsResponse() {
    }

    private BitvavoMarketsResponse(Builder builder) {
        this.market = requireNonNull(builder.market);
        this.status = requireNonNull(builder.status);
        this.base = requireNonNull(builder.base);
        this.quote = requireNonNull(builder.quote);
        this.pricePrecision = requireNonNull(builder.pricePrecision);
        this.minOrderInQuoteAsset = requireNonNull(builder.minOrderInQuoteAsset);
        this.minOrderInBaseAsset = requireNonNull(builder.minOrderInBaseAsset);
        this.orderTypes = requireNonNull(builder.orderTypes);
    }

    public BitvavoMarket getMarket() {
        return market;
    }

    public String getStatus() {
        return status;
    }

    public String getBase() {
        return base;
    }

    public String getQuote() {
        return quote;
    }

    public BigDecimal getPricePrecision() {
        return pricePrecision;
    }

    public BigDecimal getMinOrderInQuoteAsset() {
        return minOrderInQuoteAsset;
    }

    public BigDecimal getMinOrderInBaseAsset() {
        return minOrderInBaseAsset;
    }

    public List<String> getOrderTypes() {
        return orderTypes;
    }

    public static final class Builder {

        private BitvavoMarket market;
        private String status;
        private String base;
        private String quote;
        private BigDecimal pricePrecision;
        private BigDecimal minOrderInQuoteAsset;
        private BigDecimal minOrderInBaseAsset;
        private List<String> orderTypes;

        public Builder withMarket(BitvavoMarket market) {
            this.market = market;
            return this;
        }

        public Builder withStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder withBase(String base) {
            this.base = base;
            return this;
        }

        public Builder withQuote(String quote) {
            this.quote = quote;
            return this;
        }

        public Builder withPricePrecision(BigDecimal pricePrecision) {
            this.pricePrecision = pricePrecision;
            return this;
        }

        public Builder withMinOrderInQuoteAsset(BigDecimal minOrderInQuoteAsset) {
            this.minOrderInQuoteAsset = minOrderInQuoteAsset;
            return this;
        }

        public Builder withMinOrderInBaseAsset(BigDecimal minOrderInBaseAsset) {
            this.minOrderInBaseAsset = minOrderInBaseAsset;
            return this;
        }

        public Builder withOrderTypes(List<String> orderTypes) {
            this.orderTypes = orderTypes;
            return this;
        }

        public BitvavoMarketsResponse build() {
            return new BitvavoMarketsResponse(this);
        }
    }
}
