package be.davidopdebeeck.bitvavo.client.serialization.interval;

import be.davidopdebeeck.bitvavo.client.api.interval.BitvavoInterval;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class BitvavoIntervalDeserializer extends JsonDeserializer<BitvavoInterval> {

    @Override
    public BitvavoInterval deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode node = parser.readValueAsTree();
        return BitvavoInterval.fromIdentifier(node.textValue());
    }
}
