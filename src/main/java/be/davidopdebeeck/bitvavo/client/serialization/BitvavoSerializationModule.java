package be.davidopdebeeck.bitvavo.client.serialization;

import be.davidopdebeeck.bitvavo.client.api.asset.BitvavoAssetSymbol;
import be.davidopdebeeck.bitvavo.client.api.interval.BitvavoInterval;
import be.davidopdebeeck.bitvavo.client.api.market.BitvavoMarket;
import be.davidopdebeeck.bitvavo.client.api.market.book.BitvavoAsk;
import be.davidopdebeeck.bitvavo.client.api.market.book.BitvavoBid;
import be.davidopdebeeck.bitvavo.client.api.market.candles.BitvavoCandle;
import be.davidopdebeeck.bitvavo.client.api.market.candles.BitvavoMarketCandlesResponse;
import be.davidopdebeeck.bitvavo.client.api.subscription.ticker24h.BitvavoTicker24hSubscriptionResponse;
import be.davidopdebeeck.bitvavo.client.serialization.asset.BitvavoAssetSymbolDeserializer;
import be.davidopdebeeck.bitvavo.client.serialization.asset.BitvavoAssetSymbolSerializer;
import be.davidopdebeeck.bitvavo.client.serialization.book.BitvavoAskDeserializer;
import be.davidopdebeeck.bitvavo.client.serialization.book.BitvavoBidDeserializer;
import be.davidopdebeeck.bitvavo.client.serialization.candles.BitvavoCandleDeserializer;
import be.davidopdebeeck.bitvavo.client.serialization.candles.BitvavoMarketCandlesResponseDeserializer;
import be.davidopdebeeck.bitvavo.client.serialization.interval.BitvavoIntervalDeserializer;
import be.davidopdebeeck.bitvavo.client.serialization.interval.BitvavoIntervalSerializer;
import be.davidopdebeeck.bitvavo.client.serialization.market.BitvavoMarketDeserializer;
import be.davidopdebeeck.bitvavo.client.serialization.market.BitvavoMarketSerializer;
import be.davidopdebeeck.bitvavo.client.serialization.subscription.BitvavoTicker24hSubscriptionResponseDeserializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.annotation.PropertyAccessor.ALL;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

public class BitvavoSerializationModule {

    public static ObjectMapper bitvavoObjectMapper() {
        return new ObjectMapper()
            .setVisibility(ALL, NONE)
            .setVisibility(FIELD, ANY)
            .configure(FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setSerializationInclusion(NON_NULL)
            .registerModule(bitvavoSerializationModule());
    }

    public static Module bitvavoSerializationModule() {
        return new SimpleModule()
            // book
            .addDeserializer(BitvavoBid.class, new BitvavoBidDeserializer())
            .addDeserializer(BitvavoAsk.class, new BitvavoAskDeserializer())
            // market
            .addSerializer(BitvavoMarket.class, new BitvavoMarketSerializer())
            .addDeserializer(BitvavoMarket.class, new BitvavoMarketDeserializer())
            // asset
            .addSerializer(BitvavoAssetSymbol.class, new BitvavoAssetSymbolSerializer())
            .addDeserializer(BitvavoAssetSymbol.class, new BitvavoAssetSymbolDeserializer())
            // interval
            .addSerializer(BitvavoInterval.class, new BitvavoIntervalSerializer())
            .addDeserializer(BitvavoInterval.class, new BitvavoIntervalDeserializer())
            // candles
            .addDeserializer(BitvavoCandle.class, new BitvavoCandleDeserializer())
            .addDeserializer(BitvavoMarketCandlesResponse.class, new BitvavoMarketCandlesResponseDeserializer())
            // subscription
            .addDeserializer(BitvavoTicker24hSubscriptionResponse.class, new BitvavoTicker24hSubscriptionResponseDeserializer())
            ;
    }
}
