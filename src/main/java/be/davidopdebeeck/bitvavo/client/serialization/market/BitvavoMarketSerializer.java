package be.davidopdebeeck.bitvavo.client.serialization.market;

import be.davidopdebeeck.bitvavo.client.api.market.BitvavoMarket;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class BitvavoMarketSerializer extends JsonSerializer<BitvavoMarket> {

    @Override
    public void serialize(BitvavoMarket value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeString(value.toString());
    }
}
