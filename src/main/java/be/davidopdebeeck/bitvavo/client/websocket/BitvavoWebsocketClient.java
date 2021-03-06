package be.davidopdebeeck.bitvavo.client.websocket;

import be.davidopdebeeck.bitvavo.client.BitvavoClientConfiguration;
import be.davidopdebeeck.bitvavo.client.api.account.BitvavoAccountResponse;
import be.davidopdebeeck.bitvavo.client.api.assets.BitvavoAssetsRequest;
import be.davidopdebeeck.bitvavo.client.api.assets.BitvavoAssetsResponse;
import be.davidopdebeeck.bitvavo.client.api.balance.BitvavoBalanceRequest;
import be.davidopdebeeck.bitvavo.client.api.balance.BitvavoBalanceResponse;
import be.davidopdebeeck.bitvavo.client.api.deposit.BitvavoBankDepositResponse;
import be.davidopdebeeck.bitvavo.client.api.deposit.BitvavoDepositRequest;
import be.davidopdebeeck.bitvavo.client.api.deposit.BitvavoDepositResponse;
import be.davidopdebeeck.bitvavo.client.api.deposit.BitvavoDigitalAssetDepositResponse;
import be.davidopdebeeck.bitvavo.client.api.deposithistory.BitvavoBankDepositHistoryResponse;
import be.davidopdebeeck.bitvavo.client.api.deposithistory.BitvavoDepositHistoryRequest;
import be.davidopdebeeck.bitvavo.client.api.deposithistory.BitvavoDepositHistoryResponse;
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
import be.davidopdebeeck.bitvavo.client.api.order.BitvavoOrderRequest;
import be.davidopdebeeck.bitvavo.client.api.order.BitvavoOrderResponse;
import be.davidopdebeeck.bitvavo.client.api.order.cancelorder.BitvavoCancelOrderRequest;
import be.davidopdebeeck.bitvavo.client.api.order.cancelorder.BitvavoCancelOrderResponse;
import be.davidopdebeeck.bitvavo.client.api.order.cancelorder.BitvavoCancelOrdersRequest;
import be.davidopdebeeck.bitvavo.client.api.order.neworder.BitvavoNewOrderRequest;
import be.davidopdebeeck.bitvavo.client.api.order.updateorder.BitvavoUpdateOrderRequest;
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
import be.davidopdebeeck.bitvavo.client.api.trades.BitvavoTradesRequest;
import be.davidopdebeeck.bitvavo.client.api.withdrawal.BitvavoWithdrawalRequest;
import be.davidopdebeeck.bitvavo.client.api.withdrawal.BitvavoWithdrawalResponse;
import be.davidopdebeeck.bitvavo.client.api.withdrawalhistory.BitvavoWithdrawalHistoryRequest;
import be.davidopdebeeck.bitvavo.client.api.withdrawalhistory.BitvavoWithdrawalHistoryResponse;
import be.davidopdebeeck.bitvavo.client.response.BitvavoResponseHandler;
import be.davidopdebeeck.bitvavo.client.response.BitvavoResponseParser;
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
    private static final String GET_MARKET_TRADES = "getTrades";
    private static final String GET_BOOK = "getBook";
    private static final String GET_ASSETS = "getAssets";
    private static final String GET_MARKETS = "getMarkets";
    private static final String GET_TIME = "getTime";
    private static final String GET_ORDER = "privateGetOrder";
    private static final String GET_ORDERS = "privateGetOrders";
    private static final String GET_ORDERS_OPEN = "privateGetOrdersOpen";
    private static final String CREATE_ORDER = "privateCreateOrder";
    private static final String UPDATE_ORDER = "privateUpdateOrder";
    private static final String CANCEL_ORDER = "privateCancelOrder";
    private static final String CANCEL_ORDERS = "privateCancelOrders";
    private static final String GET_TRADES = "privateGetTrades";
    private static final String GET_ACCOUNT = "privateGetAccount";
    private static final String GET_BALANCE = "privateGetBalance";
    private static final String DEPOSIT_ASSETS = "privateDepositAssets";
    private static final String DEPOSIT_HISTORY = "privateGetDepositHistory";
    private static final String WITHDRAW_ASSETS = "privateWithdrawAssets";
    private static final String WITHDRAWAL_HISTORY = "privateGetWithdrawalHistory";
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
        verifyActionCanBeExecuted(1);

        websocketEndpoint.registerHandler(GET_TIME, createOneTimeUseHandler(handler, BitvavoTimeResponse.class));
        websocketEndpoint.doRequest(createRequest(GET_TIME));
    }

    public void markets(BitvavoResponseHandler<BitvavoMarketsResponse[]> handler) {
        verifyActionCanBeExecuted(1);

        websocketEndpoint.registerHandler(GET_MARKETS, createOneTimeUseHandler(handler, BitvavoMarketsResponse[].class));
        websocketEndpoint.doRequest(createRequest(GET_MARKETS));
    }

    public void markets(BitvavoMarketsRequest request, BitvavoResponseHandler<BitvavoMarketsResponse> handler) {
        verifyActionCanBeExecuted(1);

        websocketEndpoint.registerHandler(GET_MARKETS, createOneTimeUseHandler(handler, BitvavoMarketsResponse.class));
        websocketEndpoint.doRequest(createRequest(GET_MARKETS, request));
    }

    public void assets(BitvavoResponseHandler<BitvavoAssetsResponse[]> handler) {
        verifyActionCanBeExecuted(1);

        websocketEndpoint.registerHandler(GET_ASSETS, createOneTimeUseHandler(handler, BitvavoAssetsResponse[].class));
        websocketEndpoint.doRequest(createRequest(GET_ASSETS));
    }

    public void assets(BitvavoAssetsRequest request, BitvavoResponseHandler<BitvavoAssetsResponse> handler) {
        verifyActionCanBeExecuted(1);

        websocketEndpoint.registerHandler(GET_ASSETS, createOneTimeUseHandler(handler, BitvavoAssetsResponse.class));
        websocketEndpoint.doRequest(createRequest(GET_ASSETS, request));
    }

    public void marketBook(BitvavoMarket market, BitvavoResponseHandler<BitvavoMarketBookResponse> handler) {
        verifyActionCanBeExecuted(1);

        BitvavoWebsocketMarketRequest marketRequest = new BitvavoWebsocketMarketRequest.Builder()
            .withMarket(market)
            .build();

        websocketEndpoint.registerHandler(GET_BOOK, createOneTimeUseHandler(handler, BitvavoMarketBookResponse.class));
        websocketEndpoint.doRequest(createRequest(GET_BOOK, marketRequest));
    }

    public void marketBook(BitvavoMarket market, BitvavoMarketBookRequest request, BitvavoResponseHandler<BitvavoMarketBookResponse> handler) {
        verifyActionCanBeExecuted(1);

        BitvavoWebsocketMarketRequest marketRequest = new BitvavoWebsocketMarketRequest.Builder()
            .withMarket(market)
            .withRequest(request)
            .build();

        websocketEndpoint.registerHandler(GET_BOOK, createOneTimeUseHandler(handler, BitvavoMarketBookResponse.class));
        websocketEndpoint.doRequest(createRequest(GET_BOOK, marketRequest));
    }

    public void marketTrades(BitvavoMarket market, BitvavoResponseHandler<BitvavoMarketTradesResponse[]> handler) {
        verifyActionCanBeExecuted(5);

        BitvavoWebsocketMarketRequest marketRequest = new BitvavoWebsocketMarketRequest.Builder()
            .withMarket(market)
            .build();

        websocketEndpoint.registerHandler(GET_MARKET_TRADES, createOneTimeUseHandler(handler, BitvavoMarketTradesResponse[].class));
        websocketEndpoint.doRequest(createRequest(GET_MARKET_TRADES, marketRequest));
    }

    public void marketTrades(BitvavoMarket market, BitvavoMarketTradesRequest request, BitvavoResponseHandler<BitvavoMarketTradesResponse[]> handler) {
        verifyActionCanBeExecuted(5);

        BitvavoWebsocketMarketRequest marketRequest = new BitvavoWebsocketMarketRequest.Builder()
            .withMarket(market)
            .withRequest(request)
            .build();

        websocketEndpoint.registerHandler(GET_MARKET_TRADES, createOneTimeUseHandler(handler, BitvavoMarketTradesResponse[].class));
        websocketEndpoint.doRequest(createRequest(GET_MARKET_TRADES, marketRequest));
    }

    public void marketCandles(BitvavoMarket market, BitvavoResponseHandler<BitvavoMarketCandlesResponse[]> handler) {
        verifyActionCanBeExecuted(1);

        BitvavoWebsocketMarketRequest marketRequest = new BitvavoWebsocketMarketRequest.Builder()
            .withMarket(market)
            .build();

        websocketEndpoint.registerHandler(GET_CANDLES, createOneTimeUseHandler(handler, BitvavoMarketCandlesResponse[].class));
        websocketEndpoint.doRequest(createRequest(GET_CANDLES, marketRequest));
    }

    public void marketCandles(BitvavoMarket market, BitvavoMarketCandlesRequest request, BitvavoResponseHandler<BitvavoMarketCandlesResponse[]> handler) {
        verifyActionCanBeExecuted(1);

        BitvavoWebsocketMarketRequest marketRequest = new BitvavoWebsocketMarketRequest.Builder()
            .withMarket(market)
            .withRequest(request)
            .build();

        websocketEndpoint.registerHandler(GET_CANDLES, createOneTimeUseHandler(handler, BitvavoMarketCandlesResponse[].class));
        websocketEndpoint.doRequest(createRequest(GET_CANDLES, marketRequest));
    }

    public void tickerPrice(BitvavoResponseHandler<BitvavoTickerPriceResponse[]> handler) {
        verifyActionCanBeExecuted(1);

        websocketEndpoint.registerHandler(GET_TICKER_PRICE, createOneTimeUseHandler(handler, BitvavoTickerPriceResponse[].class));
        websocketEndpoint.doRequest(createRequest(GET_TICKER_PRICE));
    }

    public void tickerPrice(BitvavoTickerPriceRequest request, BitvavoResponseHandler<BitvavoTickerPriceResponse> handler) {
        verifyActionCanBeExecuted(1);

        websocketEndpoint.registerHandler(GET_TICKER_PRICE, createOneTimeUseHandler(handler, BitvavoTickerPriceResponse.class));
        websocketEndpoint.doRequest(createRequest(GET_TICKER_PRICE, request));
    }

    public void tickerBook(BitvavoResponseHandler<BitvavoTickerBookResponse[]> handler) {
        verifyActionCanBeExecuted(1);

        websocketEndpoint.registerHandler(GET_TICKER_BOOK, createOneTimeUseHandler(handler, BitvavoTickerBookResponse[].class));
        websocketEndpoint.doRequest(createRequest(GET_TICKER_BOOK));
    }

    public void tickerBook(BitvavoTickerBookRequest request, BitvavoResponseHandler<BitvavoTickerBookResponse> handler) {
        verifyActionCanBeExecuted(1);

        websocketEndpoint.registerHandler(GET_TICKER_BOOK, createOneTimeUseHandler(handler, BitvavoTickerBookResponse.class));
        websocketEndpoint.doRequest(createRequest(GET_TICKER_BOOK, request));
    }

    public void ticker24h(BitvavoResponseHandler<BitvavoTicker24hResponse[]> handler) {
        verifyActionCanBeExecuted(25);

        websocketEndpoint.registerHandler(GET_TICKER_24H, createOneTimeUseHandler(handler, BitvavoTicker24hResponse[].class));
        websocketEndpoint.doRequest(createRequest(GET_TICKER_24H));
    }

    public void ticker24h(BitvavoTicker24hRequest request, BitvavoResponseHandler<BitvavoTicker24hResponse> handler) {
        verifyActionCanBeExecuted(1);

        websocketEndpoint.registerHandler(GET_TICKER_24H, createOneTimeUseHandler(handler, BitvavoTicker24hResponse.class));
        websocketEndpoint.doRequest(createRequest(GET_TICKER_24H, request));
    }

    public void newOrder(BitvavoNewOrderRequest request, BitvavoResponseHandler<BitvavoOrderResponse> handler) {
        verifyActionCanBeExecuted(1);

        websocketEndpoint.registerHandler(CREATE_ORDER, createOneTimeUseHandler(handler, BitvavoOrderResponse.class));
        websocketEndpoint.doRequest(createRequest(CREATE_ORDER, request));
    }

    public void updateOrder(BitvavoUpdateOrderRequest request, BitvavoResponseHandler<BitvavoOrderResponse> handler) {
        verifyActionCanBeExecuted(1);

        websocketEndpoint.registerHandler(UPDATE_ORDER, createOneTimeUseHandler(handler, BitvavoOrderResponse.class));
        websocketEndpoint.doRequest(createRequest(UPDATE_ORDER, request));
    }

    public void cancelOrder(BitvavoCancelOrderRequest request, BitvavoResponseHandler<BitvavoCancelOrderResponse> handler) {
        verifyActionCanBeExecuted(1);

        websocketEndpoint.registerHandler(CANCEL_ORDER, createOneTimeUseHandler(handler, BitvavoCancelOrderResponse.class));
        websocketEndpoint.doRequest(createRequest(CANCEL_ORDER, request));
    }

    public void order(BitvavoOrderRequest request, BitvavoResponseHandler<BitvavoOrderResponse> handler) {
        verifyActionCanBeExecuted(1);

        websocketEndpoint.registerHandler(GET_ORDER, createOneTimeUseHandler(handler, BitvavoOrderResponse.class));
        websocketEndpoint.doRequest(createRequest(GET_ORDER, request));
    }

    public void orders(BitvavoOrdersRequest request, BitvavoResponseHandler<BitvavoOrderResponse[]> handler) {
        verifyActionCanBeExecuted(5);

        websocketEndpoint.registerHandler(GET_ORDERS, createOneTimeUseHandler(handler, BitvavoOrderResponse[].class));
        websocketEndpoint.doRequest(createRequest(GET_ORDERS, request));
    }

    public void cancelOrders(BitvavoCancelOrdersRequest request, BitvavoResponseHandler<BitvavoCancelOrderResponse[]> handler) {
        verifyActionCanBeExecuted(1);

        websocketEndpoint.registerHandler(CANCEL_ORDERS, createOneTimeUseHandler(handler, BitvavoCancelOrderResponse[].class));
        websocketEndpoint.doRequest(createRequest(CANCEL_ORDERS, request));
    }

    public void ordersOpen(BitvavoResponseHandler<BitvavoOrderResponse[]> handler) {
        verifyActionCanBeExecuted(25);

        websocketEndpoint.registerHandler(GET_ORDERS_OPEN, createOneTimeUseHandler(handler, BitvavoOrderResponse[].class));
        websocketEndpoint.doRequest(createRequest(GET_ORDERS_OPEN));
    }

    public void ordersOpen(BitvavoOrdersRequest request, BitvavoResponseHandler<BitvavoOrderResponse[]> handler) {
        verifyActionCanBeExecuted(1);

        websocketEndpoint.registerHandler(GET_ORDERS_OPEN, createOneTimeUseHandler(handler, BitvavoOrderResponse[].class));
        websocketEndpoint.doRequest(createRequest(GET_ORDERS_OPEN, request));
    }

    public void trades(BitvavoTradesRequest request, BitvavoResponseHandler<BitvavoMarketTradesResponse[]> handler) {
        verifyActionCanBeExecuted(5);

        websocketEndpoint.registerHandler(GET_TRADES, createOneTimeUseHandler(handler, BitvavoMarketTradesResponse[].class));
        websocketEndpoint.doRequest(createRequest(GET_TRADES, request));
    }

    public void account(BitvavoResponseHandler<BitvavoAccountResponse> handler) {
        verifyActionCanBeExecuted(1);

        websocketEndpoint.registerHandler(GET_ACCOUNT, createOneTimeUseHandler(handler, BitvavoAccountResponse.class));
        websocketEndpoint.doRequest(createRequest(GET_ACCOUNT));
    }

    public void balance(BitvavoResponseHandler<BitvavoBalanceResponse[]> handler) {
        verifyActionCanBeExecuted(5);

        websocketEndpoint.registerHandler(GET_BALANCE, createOneTimeUseHandler(handler, BitvavoBalanceResponse[].class));
        websocketEndpoint.doRequest(createRequest(GET_BALANCE));
    }

    public void balance(BitvavoBalanceRequest request, BitvavoResponseHandler<BitvavoBalanceResponse> handler) {
        verifyActionCanBeExecuted(5);

        websocketEndpoint.registerHandler(GET_BALANCE, createOneTimeUseHandler(handler, BitvavoBalanceResponse.class));
        websocketEndpoint.doRequest(createRequest(GET_BALANCE, request));
    }

    public void deposit(BitvavoDepositRequest request, BitvavoResponseHandler<BitvavoDepositResponse> handler) {
        verifyActionCanBeExecuted(1);

        websocketEndpoint.registerHandler(DEPOSIT_ASSETS, createOneTimeUseHandler(handler, BitvavoDepositResponse.class));
        websocketEndpoint.doRequest(createRequest(DEPOSIT_ASSETS, request));
    }

    public void bankDeposit(BitvavoDepositRequest request, BitvavoResponseHandler<BitvavoBankDepositResponse> handler) {
        verifyActionCanBeExecuted(1);

        websocketEndpoint.registerHandler(DEPOSIT_ASSETS, createOneTimeUseHandler(handler, BitvavoBankDepositResponse.class));
        websocketEndpoint.doRequest(createRequest(DEPOSIT_ASSETS, request));
    }

    public void digitalAssetDeposit(BitvavoDepositRequest request, BitvavoResponseHandler<BitvavoDigitalAssetDepositResponse> handler) {
        verifyActionCanBeExecuted(1);

        websocketEndpoint.registerHandler(DEPOSIT_ASSETS, createOneTimeUseHandler(handler, BitvavoDigitalAssetDepositResponse.class));
        websocketEndpoint.doRequest(createRequest(DEPOSIT_ASSETS, request));
    }

    public void depositHistory(BitvavoResponseHandler<BitvavoDepositHistoryResponse[]> handler) {
        verifyActionCanBeExecuted(5);

        websocketEndpoint.registerHandler(DEPOSIT_HISTORY, createOneTimeUseHandler(handler, BitvavoDepositHistoryResponse[].class));
        websocketEndpoint.doRequest(createRequest(DEPOSIT_HISTORY));
    }

    public void depositHistory(BitvavoDepositHistoryRequest request, BitvavoResponseHandler<BitvavoDepositHistoryResponse[]> handler) {
        verifyActionCanBeExecuted(5);

        websocketEndpoint.registerHandler(DEPOSIT_HISTORY, createOneTimeUseHandler(handler, BitvavoDepositHistoryResponse[].class));
        websocketEndpoint.doRequest(createRequest(DEPOSIT_HISTORY, request));
    }

    public void bankDepositHistory(BitvavoDepositHistoryRequest request, BitvavoResponseHandler<BitvavoBankDepositHistoryResponse[]> handler) {
        verifyActionCanBeExecuted(5);

        websocketEndpoint.registerHandler(DEPOSIT_HISTORY, createOneTimeUseHandler(handler, BitvavoBankDepositHistoryResponse[].class));
        websocketEndpoint.doRequest(createRequest(DEPOSIT_HISTORY, request));
    }

    public void digitalAssetDepositHistory(BitvavoDepositHistoryRequest request, BitvavoResponseHandler<BitvavoDigitalAssetDepositResponse[]> handler) {
        verifyActionCanBeExecuted(5);

        websocketEndpoint.registerHandler(DEPOSIT_HISTORY, createOneTimeUseHandler(handler, BitvavoDigitalAssetDepositResponse[].class));
        websocketEndpoint.doRequest(createRequest(DEPOSIT_HISTORY, request));
    }

    public void withdrawal(BitvavoWithdrawalRequest request, BitvavoResponseHandler<BitvavoWithdrawalResponse> handler) {
        verifyActionCanBeExecuted(1);

        websocketEndpoint.registerHandler(WITHDRAW_ASSETS, createOneTimeUseHandler(handler, BitvavoWithdrawalResponse.class));
        websocketEndpoint.doRequest(createRequest(WITHDRAW_ASSETS, request));
    }

    public void withdrawalHistory(BitvavoResponseHandler<BitvavoWithdrawalHistoryResponse[]> handler) {
        verifyActionCanBeExecuted(5);

        websocketEndpoint.registerHandler(WITHDRAWAL_HISTORY, createOneTimeUseHandler(handler, BitvavoWithdrawalHistoryResponse[].class));
        websocketEndpoint.doRequest(createRequest(WITHDRAWAL_HISTORY));
    }

    public void withdrawalHistory(BitvavoWithdrawalHistoryRequest request, BitvavoResponseHandler<BitvavoWithdrawalHistoryResponse[]> handler) {
        verifyActionCanBeExecuted(5);

        websocketEndpoint.registerHandler(WITHDRAWAL_HISTORY, createOneTimeUseHandler(handler, BitvavoWithdrawalHistoryResponse[].class));
        websocketEndpoint.doRequest(createRequest(WITHDRAWAL_HISTORY, request));
    }

    public void tickerSubscription(BitvavoMarket market, BitvavoResponseHandler<BitvavoTickerSubscriptionResponse> handler) {
        verifyActionCanBeExecuted(1);

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
        verifyActionCanBeExecuted(1);

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
        verifyActionCanBeExecuted(1);

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
        verifyActionCanBeExecuted(1);

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
        verifyActionCanBeExecuted(1);

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
        verifyActionCanBeExecuted(1);

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

    private void verifyActionCanBeExecuted(long actionWeight) {
        configuration.getRateLimiter().verifyActionCanBeExecuted(actionWeight);
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
        return new BitvavoWebsocketEventHandler.Builder<T>()
            .withOneTimeUse(true)
            .withResponseHandler(handler)
            .withResponseParser(new BitvavoResponseParser.Builder<>(responseClass)
                .withRateLimiter(configuration.getRateLimiter())
                .withObjectMapper(configuration.getObjectMapper())
                .build())
            .build();
    }

    private <T> BitvavoWebsocketEventHandler<T> createSubscriptionHandler(BitvavoResponseHandler<T> handler, Class<T> responseClass) {
        return new BitvavoWebsocketEventHandler.Builder<T>()
            .withOneTimeUse(false)
            .withResponseHandler(handler)
            .withResponseParser(new BitvavoResponseParser.Builder<>(responseClass)
                .withRateLimiter(configuration.getRateLimiter())
                .withObjectMapper(configuration.getObjectMapper())
                .build())
            .build();
    }
}
