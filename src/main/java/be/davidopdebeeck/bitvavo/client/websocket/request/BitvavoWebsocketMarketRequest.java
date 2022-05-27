package be.davidopdebeeck.bitvavo.client.websocket.request;

import be.davidopdebeeck.bitvavo.client.api.market.BitvavoMarket;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;

public class BitvavoWebsocketMarketRequest {

    private final BitvavoMarket market;
    @JsonUnwrapped
    private final Object request;

    private BitvavoWebsocketMarketRequest(Builder builder) {
        market = requireNonNull(builder.market);
        request = builder.request;
    }

    public BitvavoMarket getMarket() {
        return market;
    }

    public Optional<Object> getRequest() {
        return ofNullable(request);
    }

    public static final class Builder {

        private BitvavoMarket market;
        private Object request;

        public Builder withMarket(BitvavoMarket market) {
            this.market = market;
            return this;
        }

        public Builder withRequest(Object request) {
            this.request = request;
            return this;
        }

        public BitvavoWebsocketMarketRequest build() {
            return new BitvavoWebsocketMarketRequest(this);
        }
    }


}
