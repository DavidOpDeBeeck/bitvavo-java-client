package be.davidopdebeeck.bitvavo.client.serialization.websocket;

import be.davidopdebeeck.bitvavo.client.websocket.BitvavoWebsocketMessage;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

import static java.lang.String.format;

public class BitvavoWebsocketMessageDeserializer extends JsonDeserializer<BitvavoWebsocketMessage> {

    @Override
    public BitvavoWebsocketMessage deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode node = parser.readValueAsTree();

        JsonNode eventNode = node.get("event");
        if (eventNode != null) {
            return new BitvavoWebsocketMessage.Builder()
                .withIdentifier(eventNode.textValue())
                .withBody(node.toString())
                .build();
        }

        JsonNode actionNode = node.get("action");
        if (actionNode != null) {
            JsonNode responseNode = node.get("response");
            return new BitvavoWebsocketMessage.Builder()
                .withIdentifier(actionNode.textValue())
                .withBody(responseNode == null ? node.toString() : node.get("response").toString())
                .build();
        }

        throw new IllegalArgumentException(format("%s could not be deserialized to a valid BitvavoWebsocketMessage, only 'event' or 'action' are supported", node));
    }
}
