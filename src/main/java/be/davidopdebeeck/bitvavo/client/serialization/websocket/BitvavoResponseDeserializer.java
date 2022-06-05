package be.davidopdebeeck.bitvavo.client.serialization.websocket;

import be.davidopdebeeck.bitvavo.client.response.BitvavoErrorMessage;
import be.davidopdebeeck.bitvavo.client.response.BitvavoResponse;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;

import java.io.IOException;

import static be.davidopdebeeck.bitvavo.client.response.BitvavoErrorMessage.fromException;
import static be.davidopdebeeck.bitvavo.client.response.BitvavoResponse.error;
import static be.davidopdebeeck.bitvavo.client.response.BitvavoResponse.ok;

public class BitvavoResponseDeserializer extends JsonDeserializer<BitvavoResponse<?>>
    implements ContextualDeserializer {

    private static final String ERROR_CODE_FIELD_NAME = "errorCode";
    private static final String ERROR_MESSAGE_FIELD_NAME = "error";

    private JavaType valueType;

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) {
        JavaType contextualType = ctxt.getContextualType();
        BitvavoResponseDeserializer bitvavoResponseDeserializer = new BitvavoResponseDeserializer();
        bitvavoResponseDeserializer.valueType = contextualType.containedType(0);
        return bitvavoResponseDeserializer;
    }

    @Override
    public BitvavoResponse<?> deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        try {
            JsonNode node = parser.readValueAsTree();

            JsonNode errorNode = node.get("error");
            if (errorNode != null) {
                return error(new BitvavoErrorMessage.Builder()
                    .withErrorCode(node.get(ERROR_CODE_FIELD_NAME).asText())
                    .withErrorMessage(node.get(ERROR_MESSAGE_FIELD_NAME).asText())
                    .build());
            }

            return ok(context.readTreeAsValue(node, valueType));
        } catch (Exception exception) {
            return error(fromException(exception));
        }
    }
}
