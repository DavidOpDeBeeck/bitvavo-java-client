package be.davidopdebeeck.bitvavo.client;

import be.davidopdebeeck.bitvavo.client.api.assets.BitvavoAssetsRequest;
import be.davidopdebeeck.bitvavo.client.api.assets.BitvavoAssetsResponse;
import be.davidopdebeeck.bitvavo.client.api.market.BitvavoMarket;
import be.davidopdebeeck.bitvavo.client.api.market.book.BitvavoMarketBookRequest;
import be.davidopdebeeck.bitvavo.client.api.market.book.BitvavoMarketBookResponse;
import be.davidopdebeeck.bitvavo.client.api.market.candles.BitvavoMarketCandlesRequest;
import be.davidopdebeeck.bitvavo.client.api.market.candles.BitvavoMarketCandlesResponse;
import be.davidopdebeeck.bitvavo.client.api.market.trades.BitvavoMarketTradesRequest;
import be.davidopdebeeck.bitvavo.client.api.market.trades.BitvavoMarketTradesResponse;
import be.davidopdebeeck.bitvavo.client.api.markets.BitvavoMarketsRequest;
import be.davidopdebeeck.bitvavo.client.api.markets.BitvavoMarketsResponse;
import be.davidopdebeeck.bitvavo.client.api.subscription.BitvavoChannelSubscriptionRequest;
import be.davidopdebeeck.bitvavo.client.api.subscription.BitvavoSubscriptionRequest;
import be.davidopdebeeck.bitvavo.client.api.subscription.ticker.BitvavoTickerSubscriptionResponse;
import be.davidopdebeeck.bitvavo.client.api.ticker24h.BitvavoTicker24hRequest;
import be.davidopdebeeck.bitvavo.client.api.ticker24h.BitvavoTicker24hResponse;
import be.davidopdebeeck.bitvavo.client.api.tickerbook.BitvavoTickerBookRequest;
import be.davidopdebeeck.bitvavo.client.api.tickerbook.BitvavoTickerBookResponse;
import be.davidopdebeeck.bitvavo.client.api.tickerprice.BitvavoTickerPriceRequest;
import be.davidopdebeeck.bitvavo.client.api.tickerprice.BitvavoTickerPriceResponse;
import be.davidopdebeeck.bitvavo.client.api.time.BitvavoTimeResponse;
import be.davidopdebeeck.bitvavo.client.websocket.BitvavoWebsocketEndpoint;
import be.davidopdebeeck.bitvavo.client.websocket.handler.BitvavoWebsocketHandler;
import be.davidopdebeeck.bitvavo.client.websocket.handler.BitvavoWebsocketResponseHandler;
import be.davidopdebeeck.bitvavo.client.websocket.request.BitvavoWebsocketMarketRequest;

import java.util.List;

import static be.davidopdebeeck.bitvavo.client.websocket.BitvavoWebsocketEndpointFactory.createWebsocketEndpoint;
import static java.util.Objects.requireNonNull;

public class BitvavoWebsocketClient {

    private final BitvavoClientConfiguration configuration;
    private final BitvavoWebsocketEndpoint websocketEndpoint;

    public BitvavoWebsocketClient(BitvavoClientConfiguration configuration) {
        this.configuration = requireNonNull(configuration);
        this.websocketEndpoint = createWebsocketEndpoint(configuration);
    }

    public void time(BitvavoWebsocketResponseHandler<BitvavoTimeResponse> handler) {
        websocketEndpoint.action("getTime", createOneTimeUseHandler(handler, BitvavoTimeResponse.class));
    }

    public void markets(BitvavoWebsocketResponseHandler<BitvavoMarketsResponse[]> handler) {
        websocketEndpoint.action("getMarkets", createOneTimeUseHandler(handler, BitvavoMarketsResponse[].class));
    }

    public void markets(BitvavoMarketsRequest request, BitvavoWebsocketResponseHandler<BitvavoMarketsResponse> handler) {
        websocketEndpoint.action("getMarkets", request, createOneTimeUseHandler(handler, BitvavoMarketsResponse.class));
    }

    public void assets(BitvavoWebsocketResponseHandler<BitvavoAssetsResponse[]> handler) {
        websocketEndpoint.action("getAssets", createOneTimeUseHandler(handler, BitvavoAssetsResponse[].class));
    }

    public void assets(BitvavoAssetsRequest request, BitvavoWebsocketResponseHandler<BitvavoAssetsResponse> handler) {
        websocketEndpoint.action("getAssets", request, createOneTimeUseHandler(handler, BitvavoAssetsResponse.class));
    }

    public void marketBook(BitvavoMarket market, BitvavoWebsocketResponseHandler<BitvavoMarketBookResponse> handler) {
        BitvavoWebsocketMarketRequest marketRequest = new BitvavoWebsocketMarketRequest.Builder()
            .withMarket(market)
            .build();
        websocketEndpoint.action("getBook", marketRequest, createOneTimeUseHandler(handler, BitvavoMarketBookResponse.class));
    }

    public void marketBook(BitvavoMarket market, BitvavoMarketBookRequest request, BitvavoWebsocketResponseHandler<BitvavoMarketBookResponse> handler) {
        BitvavoWebsocketMarketRequest marketRequest = new BitvavoWebsocketMarketRequest.Builder()
            .withMarket(market)
            .withRequest(request)
            .build();
        websocketEndpoint.action("getBook", marketRequest, createOneTimeUseHandler(handler, BitvavoMarketBookResponse.class));
    }

    public void marketTrades(BitvavoMarket market, BitvavoWebsocketResponseHandler<BitvavoMarketTradesResponse[]> handler) {
        BitvavoWebsocketMarketRequest marketRequest = new BitvavoWebsocketMarketRequest.Builder()
            .withMarket(market)
            .build();
        websocketEndpoint.action("getTrades", marketRequest, createOneTimeUseHandler(handler, BitvavoMarketTradesResponse[].class));
    }

    public void marketTrades(BitvavoMarket market, BitvavoMarketTradesRequest request, BitvavoWebsocketResponseHandler<BitvavoMarketTradesResponse[]> handler) {
        BitvavoWebsocketMarketRequest marketRequest = new BitvavoWebsocketMarketRequest.Builder()
            .withMarket(market)
            .withRequest(request)
            .build();
        websocketEndpoint.action("getTrades", marketRequest, createOneTimeUseHandler(handler, BitvavoMarketTradesResponse[].class));
    }

    public void marketCandles(BitvavoMarket market, BitvavoWebsocketResponseHandler<BitvavoMarketCandlesResponse[]> handler) {
        BitvavoWebsocketMarketRequest marketRequest = new BitvavoWebsocketMarketRequest.Builder()
            .withMarket(market)
            .build();
        websocketEndpoint.action("getCandles", marketRequest, createOneTimeUseHandler(handler, BitvavoMarketCandlesResponse[].class));
    }

    public void marketCandles(BitvavoMarket market, BitvavoMarketCandlesRequest request, BitvavoWebsocketResponseHandler<BitvavoMarketCandlesResponse[]> handler) {
        BitvavoWebsocketMarketRequest marketRequest = new BitvavoWebsocketMarketRequest.Builder()
            .withMarket(market)
            .withRequest(request)
            .build();
        websocketEndpoint.action("getCandles", marketRequest, createOneTimeUseHandler(handler, BitvavoMarketCandlesResponse[].class));
    }

    public void tickerPrice(BitvavoWebsocketResponseHandler<BitvavoTickerPriceResponse[]> handler) {
        websocketEndpoint.action("getTickerPrice", createOneTimeUseHandler(handler, BitvavoTickerPriceResponse[].class));
    }

    public void tickerPrice(BitvavoTickerPriceRequest request, BitvavoWebsocketResponseHandler<BitvavoTickerPriceResponse> handler) {
        websocketEndpoint.action("getTickerPrice", request, createOneTimeUseHandler(handler, BitvavoTickerPriceResponse.class));
    }

    public void tickerBook(BitvavoWebsocketResponseHandler<BitvavoTickerBookResponse[]> handler) {
        websocketEndpoint.action("getTickerBook", createOneTimeUseHandler(handler, BitvavoTickerBookResponse[].class));
    }

    public void tickerBook(BitvavoTickerBookRequest request, BitvavoWebsocketResponseHandler<BitvavoTickerBookResponse> handler) {
        websocketEndpoint.action("getTickerBook", request, createOneTimeUseHandler(handler, BitvavoTickerBookResponse.class));
    }

    public void ticker24h(BitvavoWebsocketResponseHandler<BitvavoTicker24hResponse[]> handler) {
        websocketEndpoint.action("getTicker24h", createOneTimeUseHandler(handler, BitvavoTicker24hResponse[].class));
    }

    public void ticker24h(BitvavoTicker24hRequest request, BitvavoWebsocketResponseHandler<BitvavoTicker24hResponse> handler) {
        websocketEndpoint.action("getTicker24h", request, createOneTimeUseHandler(handler, BitvavoTicker24hResponse.class));
    }

    public void tickerSubscription(BitvavoMarket market, BitvavoWebsocketResponseHandler<BitvavoTickerSubscriptionResponse> handler) {
        BitvavoSubscriptionRequest subscriptionRequest = new BitvavoSubscriptionRequest.Builder()
            .withChannels(List.of(new BitvavoChannelSubscriptionRequest.Builder()
                .withName("ticker")
                .withMarkets(List.of(market))
                .build()))
            .build();

        websocketEndpoint.subscribe("ticker", subscriptionRequest, createSubscriptionHandler(handler, BitvavoTickerSubscriptionResponse.class));
    }

    private <T> BitvavoWebsocketHandler<T> createOneTimeUseHandler(BitvavoWebsocketResponseHandler<T> handler, Class<T> responseClass) {
        return new BitvavoWebsocketHandler.Builder<>(responseClass)
            .withOneTimeUse(true)
            .withObjectMapper(configuration.getObjectMapper())
            .withResponseHandler(handler)
            .build();
    }

    private <T> BitvavoWebsocketHandler<T> createSubscriptionHandler(BitvavoWebsocketResponseHandler<T> handler, Class<T> responseClass) {
        return new BitvavoWebsocketHandler.Builder<>(responseClass)
            .withOneTimeUse(false)
            .withObjectMapper(configuration.getObjectMapper())
            .withResponseHandler(handler)
            .build();
    }
}
