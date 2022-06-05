package be.davidopdebeeck.bitvavo.client.api.order.cancelorder;

import static java.util.Objects.requireNonNull;

public class BitvavoCancelOrderResponse {

    private String orderId;

    private BitvavoCancelOrderResponse() {
    }

    private BitvavoCancelOrderResponse(Builder builder) {
        orderId = requireNonNull(builder.orderId);
    }

    public String getOrderId() {
        return orderId;
    }

    public static final class Builder {

        private String orderId;

        public Builder withOrderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public BitvavoCancelOrderResponse build() {
            return new BitvavoCancelOrderResponse(this);
        }
    }
}
