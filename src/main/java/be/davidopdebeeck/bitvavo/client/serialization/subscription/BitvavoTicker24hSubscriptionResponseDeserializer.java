package be.davidopdebeeck.bitvavo.client.serialization.subscription;

import be.davidopdebeeck.bitvavo.client.api.market.BitvavoMarket;
import be.davidopdebeeck.bitvavo.client.api.subscription.ticker24h.BitvavoTicker24hSubscriptionResponse;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;

public class BitvavoTicker24hSubscriptionResponseDeserializer extends JsonDeserializer<BitvavoTicker24hSubscriptionResponse> {

    @Override
    public BitvavoTicker24hSubscriptionResponse deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode node = parser.readValueAsTree();
        JsonNode dataNode = node.get("data").get(0);

        return new BitvavoTicker24hSubscriptionResponse.Builder()
            .withMarket(context.readTreeAsValue(dataNode.get("market"), BitvavoMarket.class))
            .withOpen(new BigDecimal(dataNode.get("open").asText()))
            .withHigh(new BigDecimal(dataNode.get("high").asText()))
            .withLow(new BigDecimal(dataNode.get("low").asText()))
            .withLast(new BigDecimal(dataNode.get("last").asText()))
            .withVolume(new BigDecimal(dataNode.get("volume").asText()))
            .withVolumeQuote(new BigDecimal(dataNode.get("volumeQuote").asText()))
            .withBid(new BigDecimal(dataNode.get("bid").asText()))
            .withBidSize(new BigDecimal(dataNode.get("bidSize").asText()))
            .withAsk(new BigDecimal(dataNode.get("ask").asText()))
            .withAskSize(new BigDecimal(dataNode.get("askSize").asText()))
            .withTimestamp(context.readTreeAsValue(dataNode.get("timestamp"), Instant.class))
            .build();
    }
}
