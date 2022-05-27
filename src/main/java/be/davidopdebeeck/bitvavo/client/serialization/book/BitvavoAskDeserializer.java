package be.davidopdebeeck.bitvavo.client.serialization.book;

import be.davidopdebeeck.bitvavo.client.api.market.book.BitvavoAsk;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.math.BigDecimal;

public class BitvavoAskDeserializer extends JsonDeserializer<BitvavoAsk> {

    @Override
    public BitvavoAsk deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode node = parser.readValueAsTree();
        return new BitvavoAsk.Builder()
            .withPrice(new BigDecimal(node.get(0).textValue()))
            .withSize(new BigDecimal(node.get(1).textValue()))
            .build();
    }
}
