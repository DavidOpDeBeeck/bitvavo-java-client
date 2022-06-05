package be.davidopdebeeck.bitvavo.client.websocket;

import be.davidopdebeeck.bitvavo.client.BitvavoClientConfiguration;
import be.davidopdebeeck.bitvavo.client.api.assets.BitvavoAssetsRequest;
import be.davidopdebeeck.bitvavo.client.api.assets.BitvavoAssetsResponse;
import be.davidopdebeeck.bitvavo.client.api.interval.BitvavoInterval;
import be.davidopdebeeck.bitvavo.client.api.market.BitvavoMarket;
import be.davidopdebeeck.bitvavo.client.api.market.book.BitvavoMarketBookRequest;
import be.davidopdebeeck.bitvavo.client.api.market.book.BitvavoMarketBookResponse;
import be.davidopdebeeck.bitvavo.client.api.market.candles.BitvavoMarketCandlesRequest;
import be.davidopdebeeck.bitvavo.client.api.market.candles.BitvavoMarketCandlesResponse;
import be.davidopdebeeck.bitvavo.client.api.market.trades.BitvavoMarketTradesRequest;
import be.davidopdebeeck.bitvavo.client.api.market.trades.BitvavoMarketTradesResponse;
import be.davidopdebeeck.bitvavo.client.api.markets.BitvavoMarketsRequest;
import be.davidopdebeeck.bitvavo.client.api.markets.BitvavoMarketsResponse;
import be.davidopdebeeck.bitvavo.client.api.order.BitvavoOrderResponse;
import be.davidopdebeeck.bitvavo.client.api.order.neworder.BitvavoNewOrderRequest;
import be.davidopdebeeck.bitvavo.client.api.orders.BitvavoOrdersRequest;
import be.davidopdebeeck.bitvavo.client.api.subscription.BitvavoChannelSubscriptionRequest;
import be.davidopdebeeck.bitvavo.client.api.subscription.BitvavoSubscriptionRequest;
import be.davidopdebeeck.bitvavo.client.api.subscription.account.BitvavoAccountFillSubscriptionResponse;
import be.davidopdebeeck.bitvavo.client.api.subscription.account.BitvavoAccountOrderSubscriptionResponse;
import be.davidopdebeeck.bitvavo.client.api.subscription.book.BitvavoBookSubscriptionResponse;
import be.davidopdebeeck.bitvavo.client.api.subscription.candles.BitvavoCandlesSubscriptionRequest;
import be.davidopdebeeck.bitvavo.client.api.subscription.candles.BitvavoCandlesSubscriptionResponse;
import be.davidopdebeeck.bitvavo.client.api.subscription.ticker.BitvavoTickerSubscriptionResponse;
import be.davidopdebeeck.bitvavo.client.api.subscription.ticker24h.BitvavoTicker24hSubscriptionResponse;
import be.davidopdebeeck.bitvavo.client.api.subscription.trades.BitvavoTradesSubscriptionResponse;
import be.davidopdebeeck.bitvavo.client.api.ticker24h.BitvavoTicker24hRequest;
import be.davidopdebeeck.bitvavo.client.api.ticker24h.BitvavoTicker24hResponse;
import be.davidopdebeeck.bitvavo.client.api.tickerbook.BitvavoTickerBookRequest;
import be.davidopdebeeck.bitvavo.client.api.tickerbook.BitvavoTickerBookResponse;
import be.davidopdebeeck.bitvavo.client.api.tickerprice.BitvavoTickerPriceRequest;
import be.davidopdebeeck.bitvavo.client.api.tickerprice.BitvavoTickerPriceResponse;
import be.davidopdebeeck.bitvavo.client.api.time.BitvavoTimeResponse;
import be.davidopdebeeck.bitvavo.client.response.BitvavoResponseHandler;
import be.davidopdebeeck.bitvavo.client.websocket.handler.error.BitvavoWebsocketErrorHandler;
import be.davidopdebeeck.bitvavo.client.websocket.handler.event.BitvavoWebsocketEventHandler;
import be.davidopdebeeck.bitvavo.client.websocket.request.BitvavoWebsocketMarketRequest;
import be.davidopdebeeck.bitvavo.client.websocket.request.BitvavoWebsocketRequest;

import java.util.List;

import static be.davidopdebeeck.bitvavo.client.websocket.BitvavoWebsocketEndpointFactory.createWebsocketEndpoint;
import static java.util.Objects.requireNonNull;

public class BitvavoWebsocketClient {

    private static final String GET_TICKER_24H = "getTicker24h";
    private static final String GET_TICKER_BOOK = "getTickerBook";
    private static final String GET_TICKER_PRICE = "getTickerPrice";
    private static final String GET_CANDLES = "getCandles";
    private static final String GET_TRADES = "getTrades";
    private static final String GET_BOOK = "getBook";
    private static final String GET_ASSETS = "getAssets";
    private static final String GET_MARKETS = "getMarkets";
    private static final String GET_TIME = "getTime";
    private static final String GET_ORDERS = "privateGetOrders";
    private static final String CREATE_ORDERS = "privateCreateOrder";
    private static final String SUBSCRIBE = "subscribe";
    private static final String TICKER = "ticker";
    private static final String TICKER_24H = "ticker24h";
    private static final String ACCOUNT = "account";
    private static final String ORDER = "order";
    private static final String FILL = "fill";
    private static final String BOOK = "book";
    private static final String TRADES = "trades";
    private static final String TRADE = "trade";
    private static final String CANDLES = "candles";
    private static final String CANDLE = "candle";

    private final BitvavoClientConfiguration configuration;
    private final BitvavoWebsocketEndpoint websocketEndpoint;

    public BitvavoWebsocketClient(BitvavoClientConfiguration configuration) {
        this.configuration = requireNonNull(configuration);
        this.websocketEndpoint = createWebsocketEndpoint(configuration);
    }

    public void time(BitvavoResponseHandler<BitvavoTimeResponse> handler) {
        websocketEndpoint.registerHandler(GET_TIME, createOneTimeUseHandler(handler, BitvavoTimeResponse.class));
        websocketEndpoint.doRequest(createRequest(GET_TIME));
    }

    public void markets(BitvavoResponseHandler<BitvavoMarketsResponse[]> handler) {
        websocketEndpoint.registerHandler(GET_MARKETS, createOneTimeUseHandler(handler, BitvavoMarketsResponse[].class));
        websocketEndpoint.doRequest(createRequest(GET_MARKETS));
    }

    public void markets(BitvavoMarketsRequest request, BitvavoResponseHandler<BitvavoMarketsResponse> handler) {
        websocketEndpoint.registerHandler(GET_MARKETS, createOneTimeUseHandler(handler, BitvavoMarketsResponse.class));
        websocketEndpoint.doRequest(createRequest(GET_MARKETS, request));
    }

    public void assets(BitvavoResponseHandler<BitvavoAssetsResponse[]> handler) {
        websocketEndpoint.registerHandler(GET_ASSETS, createOneTimeUseHandler(handler, BitvavoAssetsResponse[].class));
        websocketEndpoint.doRequest(createRequest(GET_ASSETS));
    }

    public void assets(BitvavoAssetsRequest request, BitvavoResponseHandler<BitvavoAssetsResponse> handler) {
        websocketEndpoint.registerHandler(GET_ASSETS, createOneTimeUseHandler(handler, BitvavoAssetsResponse.class));
        websocketEndpoint.doRequest(createRequest(GET_ASSETS, request));
    }

    public void marketBook(BitvavoMarket market, BitvavoResponseHandler<BitvavoMarketBookResponse> handler) {
        BitvavoWebsocketMarketRequest marketRequest = new BitvavoWebsocketMarketRequest.Builder()
            .withMarket(market)
            .build();

        websocketEndpoint.registerHandler(GET_BOOK, createOneTimeUseHandler(handler, BitvavoMarketBookResponse.class));
        websocketEndpoint.doRequest(createRequest(GET_BOOK, marketRequest));
    }

    public void marketBook(BitvavoMarket market, BitvavoMarketBookRequest request, BitvavoResponseHandler<BitvavoMarketBookResponse> handler) {
        BitvavoWebsocketMarketRequest marketRequest = new BitvavoWebsocketMarketRequest.Builder()
            .withMarket(market)
            .withRequest(request)
            .build();

        websocketEndpoint.registerHandler(GET_BOOK, createOneTimeUseHandler(handler, BitvavoMarketBookResponse.class));
        websocketEndpoint.doRequest(createRequest(GET_BOOK, marketRequest));
    }

    public void marketTrades(BitvavoMarket market, BitvavoResponseHandler<BitvavoMarketTradesResponse[]> handler) {
        BitvavoWebsocketMarketRequest marketRequest = new BitvavoWebsocketMarketRequest.Builder()
            .withMarket(market)
            .build();

        websocketEndpoint.registerHandler(GET_TRADES, createOneTimeUseHandler(handler, BitvavoMarketTradesResponse[].class));
        websocketEndpoint.doRequest(createRequest(GET_TRADES, marketRequest));
    }

    public void marketTrades(BitvavoMarket market, BitvavoMarketTradesRequest request, BitvavoResponseHandler<BitvavoMarketTradesResponse[]> handler) {
        BitvavoWebsocketMarketRequest marketRequest = new BitvavoWebsocketMarketRequest.Builder()
            .withMarket(market)
            .withRequest(request)
            .build();

        websocketEndpoint.registerHandler(GET_TRADES, createOneTimeUseHandler(handler, BitvavoMarketTradesResponse[].class));
        websocketEndpoint.doRequest(createRequest(GET_TRADES, marketRequest));
    }

    public void marketCandles(BitvavoMarket market, BitvavoResponseHandler<BitvavoMarketCandlesResponse[]> handler) {
        BitvavoWebsocketMarketRequest marketRequest = new BitvavoWebsocketMarketRequest.Builder()
            .withMarket(market)
            .build();

        websocketEndpoint.registerHandler(GET_CANDLES, createOneTimeUseHandler(handler, BitvavoMarketCandlesResponse[].class));
        websocketEndpoint.doRequest(createRequest(GET_CANDLES, marketRequest));
    }

    public void marketCandles(BitvavoMarket market, BitvavoMarketCandlesRequest request, BitvavoResponseHandler<BitvavoMarketCandlesResponse[]> handler) {
        BitvavoWebsocketMarketRequest marketRequest = new BitvavoWebsocketMarketRequest.Builder()
            .withMarket(market)
            .withRequest(request)
            .build();

        websocketEndpoint.registerHandler(GET_CANDLES, createOneTimeUseHandler(handler, BitvavoMarketCandlesResponse[].class));
        websocketEndpoint.doRequest(createRequest(GET_CANDLES, marketRequest));
    }

    public void tickerPrice(BitvavoResponseHandler<BitvavoTickerPriceResponse[]> handler) {
        websocketEndpoint.registerHandler(GET_TICKER_PRICE, createOneTimeUseHandler(handler, BitvavoTickerPriceResponse[].class));
        websocketEndpoint.doRequest(createRequest(GET_TICKER_PRICE));
    }

    public void tickerPrice(BitvavoTickerPriceRequest request, BitvavoResponseHandler<BitvavoTickerPriceResponse> handler) {
        websocketEndpoint.registerHandler(GET_TICKER_PRICE, createOneTimeUseHandler(handler, BitvavoTickerPriceResponse.class));
        websocketEndpoint.doRequest(createRequest(GET_TICKER_PRICE, request));
    }

    public void tickerBook(BitvavoResponseHandler<BitvavoTickerBookResponse[]> handler) {
        websocketEndpoint.registerHandler(GET_TICKER_BOOK, createOneTimeUseHandler(handler, BitvavoTickerBookResponse[].class));
        websocketEndpoint.doRequest(createRequest(GET_TICKER_BOOK));
    }

    public void tickerBook(BitvavoTickerBookRequest request, BitvavoResponseHandler<BitvavoTickerBookResponse> handler) {
        websocketEndpoint.registerHandler(GET_TICKER_BOOK, createOneTimeUseHandler(handler, BitvavoTickerBookResponse.class));
        websocketEndpoint.doRequest(createRequest(GET_TICKER_BOOK, request));
    }

    public void ticker24h(BitvavoResponseHandler<BitvavoTicker24hResponse[]> handler) {
        websocketEndpoint.registerHandler(GET_TICKER_24H, createOneTimeUseHandler(handler, BitvavoTicker24hResponse[].class));
        websocketEndpoint.doRequest(createRequest(GET_TICKER_24H));
    }

    public void ticker24h(BitvavoTicker24hRequest request, BitvavoResponseHandler<BitvavoTicker24hResponse> handler) {
        websocketEndpoint.registerHandler(GET_TICKER_24H, createOneTimeUseHandler(handler, BitvavoTicker24hResponse.class));
        websocketEndpoint.doRequest(createRequest(GET_TICKER_24H, request));
    }

    public void newOrder(BitvavoNewOrderRequest request, BitvavoResponseHandler<BitvavoOrderResponse> handler) {
        websocketEndpoint.registerHandler(CREATE_ORDERS, createOneTimeUseHandler(handler, BitvavoOrderResponse.class));
        websocketEndpoint.doRequest(createRequest(CREATE_ORDERS, request));
    }

    public void orders(BitvavoOrdersRequest request, BitvavoResponseHandler<BitvavoOrderResponse[]> handler) {
        websocketEndpoint.registerHandler(GET_ORDERS, createOneTimeUseHandler(handler, BitvavoOrderResponse[].class));
        websocketEndpoint.doRequest(createRequest(GET_ORDERS, request));
    }

    public void tickerSubscription(BitvavoMarket market, BitvavoResponseHandler<BitvavoTickerSubscriptionResponse> handler) {
        BitvavoSubscriptionRequest subscriptionRequest = new BitvavoSubscriptionRequest.Builder()
            .withChannels(List.of(new BitvavoChannelSubscriptionRequest.Builder()
                .withName(TICKER)
                .withMarkets(List.of(market))
                .build()))
            .build();

        websocketEndpoint.registerHandler(TICKER, createSubscriptionHandler(handler, BitvavoTickerSubscriptionResponse.class));
        websocketEndpoint.doRequest(createRequest(SUBSCRIBE, subscriptionRequest));
    }

    public void ticker24hSubscription(BitvavoMarket market, BitvavoResponseHandler<BitvavoTicker24hSubscriptionResponse> handler) {
        BitvavoSubscriptionRequest subscriptionRequest = new BitvavoSubscriptionRequest.Builder()
            .withChannels(List.of(new BitvavoChannelSubscriptionRequest.Builder()
                .withName(TICKER_24H)
                .withMarkets(List.of(market))
                .build()))
            .build();

        websocketEndpoint.registerHandler(TICKER_24H, createSubscriptionHandler(handler, BitvavoTicker24hSubscriptionResponse.class));
        websocketEndpoint.doRequest(createRequest(SUBSCRIBE, subscriptionRequest));
    }

    public void accountSubscription(BitvavoMarket market,
                                    BitvavoResponseHandler<BitvavoAccountFillSubscriptionResponse> fillHandler,
                                    BitvavoResponseHandler<BitvavoAccountOrderSubscriptionResponse> orderHandler) {
        BitvavoSubscriptionRequest subscriptionRequest = new BitvavoSubscriptionRequest.Builder()
            .withChannels(List.of(new BitvavoChannelSubscriptionRequest.Builder()
                .withName(ACCOUNT)
                .withMarkets(List.of(market))
                .build()))
            .build();

        websocketEndpoint.registerHandler(FILL, createSubscriptionHandler(fillHandler, BitvavoAccountFillSubscriptionResponse.class));
        websocketEndpoint.registerHandler(ORDER, createSubscriptionHandler(orderHandler, BitvavoAccountOrderSubscriptionResponse.class));

        websocketEndpoint.doRequest(createRequest(SUBSCRIBE, subscriptionRequest));
    }

    public void candlesSubscription(BitvavoMarket market, BitvavoInterval interval, BitvavoResponseHandler<BitvavoCandlesSubscriptionResponse> handler) {
        BitvavoSubscriptionRequest subscriptionRequest = new BitvavoSubscriptionRequest.Builder()
            .withChannels(List.of(new BitvavoChannelSubscriptionRequest.Builder()
                .withName(CANDLES)
                .withMarkets(List.of(market))
                .withRequest(new BitvavoCandlesSubscriptionRequest.Builder()
                    .withIntervals(List.of(interval))
                    .build())
                .build()))
            .build();

        websocketEndpoint.registerHandler(CANDLE, createSubscriptionHandler(handler, BitvavoCandlesSubscriptionResponse.class));
        websocketEndpoint.doRequest(createRequest(SUBSCRIBE, subscriptionRequest));
    }

    public void tradesSubscription(BitvavoMarket market, BitvavoResponseHandler<BitvavoTradesSubscriptionResponse> handler) {
        BitvavoSubscriptionRequest subscriptionRequest = new BitvavoSubscriptionRequest.Builder()
            .withChannels(List.of(new BitvavoChannelSubscriptionRequest.Builder()
                .withName(TRADES)
                .withMarkets(List.of(market))
                .build()))
            .build();

        websocketEndpoint.registerHandler(TRADE, createSubscriptionHandler(handler, BitvavoTradesSubscriptionResponse.class));
        websocketEndpoint.doRequest(createRequest(SUBSCRIBE, subscriptionRequest));
    }

    public void bookSubscription(BitvavoMarket market, BitvavoResponseHandler<BitvavoBookSubscriptionResponse> handler) {
        BitvavoSubscriptionRequest subscriptionRequest = new BitvavoSubscriptionRequest.Builder()
            .withChannels(List.of(new BitvavoChannelSubscriptionRequest.Builder()
                .withName(BOOK)
                .withMarkets(List.of(market))
                .build()))
            .build();

        websocketEndpoint.registerHandler(BOOK, createSubscriptionHandler(handler, BitvavoBookSubscriptionResponse.class));
        websocketEndpoint.doRequest(createRequest(SUBSCRIBE, subscriptionRequest));
    }

    public void errorHandler(BitvavoWebsocketErrorHandler handler) {
        websocketEndpoint.registerHandler(handler);
    }

    private BitvavoWebsocketRequest createRequest(String action) {
        return createRequest(action, null);
    }

    private BitvavoWebsocketRequest createRequest(String action, Object request) {
        return new BitvavoWebsocketRequest.Builder()
            .withAction(action)
            .withRequest(request)
            .build();
    }

    private <T> BitvavoWebsocketEventHandler<T> createOneTimeUseHandler(BitvavoResponseHandler<T> handler, Class<T> responseClass) {
        return new BitvavoWebsocketEventHandler.Builder<>(responseClass)
            .withOneTimeUse(true)
            .withObjectMapper(configuration.getObjectMapper())
            .withResponseHandler(handler)
            .build();
    }

    private <T> BitvavoWebsocketEventHandler<T> createSubscriptionHandler(BitvavoResponseHandler<T> handler, Class<T> responseClass) {
        return new BitvavoWebsocketEventHandler.Builder<>(responseClass)
            .withOneTimeUse(false)
            .withObjectMapper(configuration.getObjectMapper())
            .withResponseHandler(handler)
            .build();
    }
}
