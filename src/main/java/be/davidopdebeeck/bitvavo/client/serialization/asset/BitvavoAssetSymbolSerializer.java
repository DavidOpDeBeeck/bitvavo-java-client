package be.davidopdebeeck.bitvavo.client.serialization.asset;

import be.davidopdebeeck.bitvavo.client.api.asset.BitvavoAssetSymbol;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class BitvavoAssetSymbolSerializer extends JsonSerializer<BitvavoAssetSymbol> {

    @Override
    public void serialize(BitvavoAssetSymbol value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeString(value.toString());
    }
}
