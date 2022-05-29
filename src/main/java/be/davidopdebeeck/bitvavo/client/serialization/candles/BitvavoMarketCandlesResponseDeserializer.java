package be.davidopdebeeck.bitvavo.client.serialization.candles;

import be.davidopdebeeck.bitvavo.client.api.market.candles.BitvavoCandle;
import be.davidopdebeeck.bitvavo.client.api.market.candles.BitvavoMarketCandlesResponse;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BitvavoMarketCandlesResponseDeserializer extends JsonDeserializer<BitvavoMarketCandlesResponse> {

    @Override
    public BitvavoMarketCandlesResponse deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode node = parser.readValueAsTree();

        List<BitvavoCandle> candles = new ArrayList<>();
        for (JsonNode candle : node) {
            candles.add(context.readTreeAsValue(candle, BitvavoCandle.class));
        }

        return new BitvavoMarketCandlesResponse.Builder()
            .withCandles(candles)
            .build();
    }
}
