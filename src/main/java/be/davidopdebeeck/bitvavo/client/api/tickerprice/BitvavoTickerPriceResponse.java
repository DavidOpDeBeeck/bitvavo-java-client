package be.davidopdebeeck.bitvavo.client.api.tickerprice;

import be.davidopdebeeck.bitvavo.client.api.market.BitvavoMarket;

import java.math.BigDecimal;

import static java.util.Objects.requireNonNull;

public class BitvavoTickerPriceResponse {

    private BitvavoMarket market;
    private BigDecimal price;

    private BitvavoTickerPriceResponse() {
    }

    private BitvavoTickerPriceResponse(Builder builder) {
        market = requireNonNull(builder.market);
        price = requireNonNull(builder.price);
    }

    public BitvavoMarket getMarket() {
        return market;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public static final class Builder {

        private BitvavoMarket market;
        private BigDecimal price;

        public Builder withMarket(BitvavoMarket market) {
            this.market = market;
            return this;
        }

        public Builder withPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public BitvavoTickerPriceResponse build() {
            return new BitvavoTickerPriceResponse(this);
        }
    }
}
