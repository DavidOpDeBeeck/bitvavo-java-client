package be.davidopdebeeck.bitvavo.client.serialization.asset;

import be.davidopdebeeck.bitvavo.client.api.asset.BitvavoAssetSymbol;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

import static be.davidopdebeeck.bitvavo.client.api.asset.BitvavoAssetSymbol.symbol;

public class BitvavoAssetSymbolDeserializer extends JsonDeserializer<BitvavoAssetSymbol> {

    @Override
    public BitvavoAssetSymbol deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode node = parser.readValueAsTree();
        return symbol(node.textValue());
    }
}
