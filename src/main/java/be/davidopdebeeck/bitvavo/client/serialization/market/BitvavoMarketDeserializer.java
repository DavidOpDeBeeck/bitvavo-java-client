package be.davidopdebeeck.bitvavo.client.serialization.market;

import be.davidopdebeeck.bitvavo.client.api.market.BitvavoMarket;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

import static be.davidopdebeeck.bitvavo.client.api.asset.BitvavoAssetSymbol.symbol;
import static be.davidopdebeeck.bitvavo.client.api.market.BitvavoMarket.market;

public class BitvavoMarketDeserializer extends JsonDeserializer<BitvavoMarket> {

    @Override
    public BitvavoMarket deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode node = parser.readValueAsTree();
        String[] nodeTextSplit = node.textValue().split("-");

        return market(symbol(nodeTextSplit[0]), symbol(nodeTextSplit[1]));
    }
}
