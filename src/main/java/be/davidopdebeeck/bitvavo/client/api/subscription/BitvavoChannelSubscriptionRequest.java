package be.davidopdebeeck.bitvavo.client.api.subscription;

import be.davidopdebeeck.bitvavo.client.api.market.BitvavoMarket;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

public class BitvavoChannelSubscriptionRequest {

    private final String name;
    private final List<BitvavoMarket> markets;
    @JsonUnwrapped
    private final Object request;

    private BitvavoChannelSubscriptionRequest(Builder builder) {
        name = requireNonNull(builder.name);
        markets = requireNonNull(builder.markets);
        request = builder.request;
    }

    public String getName() {
        return name;
    }

    public List<BitvavoMarket> getMarkets() {
        return markets;
    }

    public Optional<Object> getRequest() {
        return Optional.ofNullable(request);
    }

    public static final class Builder {

        private String name;
        private List<BitvavoMarket> markets;
        private Object request;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withMarkets(List<BitvavoMarket> markets) {
            this.markets = markets;
            return this;
        }

        public Builder withRequest(Object request) {
            this.request = request;
            return this;
        }

        public BitvavoChannelSubscriptionRequest build() {
            return new BitvavoChannelSubscriptionRequest(this);
        }
    }
}
