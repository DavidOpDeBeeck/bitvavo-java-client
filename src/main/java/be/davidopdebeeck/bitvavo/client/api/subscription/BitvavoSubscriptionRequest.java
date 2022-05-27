package be.davidopdebeeck.bitvavo.client.api.subscription;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class BitvavoSubscriptionRequest {

    private final List<BitvavoChannelSubscriptionRequest> channels;

    private BitvavoSubscriptionRequest(Builder builder) {
        channels = requireNonNull(builder.channels);
    }

    public List<BitvavoChannelSubscriptionRequest> getChannels() {
        return channels;
    }

    public static final class Builder {

        private List<BitvavoChannelSubscriptionRequest> channels;

        public Builder withChannels(List<BitvavoChannelSubscriptionRequest> channels) {
            this.channels = channels;
            return this;
        }

        public BitvavoSubscriptionRequest build() {
            return new BitvavoSubscriptionRequest(this);
        }
    }
}
