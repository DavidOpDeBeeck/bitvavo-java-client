package be.davidopdebeeck.bitvavo.client.serialization.interval;

import be.davidopdebeeck.bitvavo.client.api.interval.BitvavoInterval;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class BitvavoIntervalSerializer extends JsonSerializer<BitvavoInterval> {

    @Override
    public void serialize(BitvavoInterval value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeString(value.getIdentifier());
    }
}
