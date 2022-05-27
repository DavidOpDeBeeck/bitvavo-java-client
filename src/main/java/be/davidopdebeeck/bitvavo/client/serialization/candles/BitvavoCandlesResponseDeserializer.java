package be.davidopdebeeck.bitvavo.client.serialization.candles;

import be.davidopdebeeck.bitvavo.client.api.market.candles.BitvavoMarketCandlesResponse;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.math.BigDecimal;

public class BitvavoCandlesResponseDeserializer extends JsonDeserializer<BitvavoMarketCandlesResponse> {

    @Override
    public BitvavoMarketCandlesResponse deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode node = parser.readValueAsTree();

        return new BitvavoMarketCandlesResponse.Builder()
            .withTimestamp(node.get(0).asLong())
            .withOpen(new BigDecimal(node.get(1).asText()))
            .withHigh(new BigDecimal(node.get(2).asText()))
            .withLow(new BigDecimal(node.get(3).asText()))
            .withClose(new BigDecimal(node.get(4).asText()))
            .withVolume(new BigDecimal(node.get(5).asText()))
            .build();
    }
}
