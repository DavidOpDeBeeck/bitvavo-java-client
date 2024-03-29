package be.davidopdebeeck.bitvavo.client.http;

import be.davidopdebeeck.bitvavo.client.BitvavoClientConfiguration;
import be.davidopdebeeck.bitvavo.client.BitvavoClientException;
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
import be.davidopdebeeck.bitvavo.client.api.orders.BitvavoOrdersOpenRequest;
import be.davidopdebeeck.bitvavo.client.api.orders.BitvavoOrdersRequest;
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
import be.davidopdebeeck.bitvavo.client.http.request.BitvavoHttpRequest;
import be.davidopdebeeck.bitvavo.client.response.BitvavoResponse;
import be.davidopdebeeck.bitvavo.client.response.BitvavoResponseParser;
import be.davidopdebeeck.bitvavo.client.utils.URIBuilder;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.Map;

import static be.davidopdebeeck.bitvavo.client.response.BitvavoResponseMetadata.metadata;
import static java.net.http.HttpClient.newHttpClient;
import static java.net.http.HttpResponse.BodyHandlers.ofString;
import static java.util.Objects.requireNonNull;

public class BitvavoHttpClient {

    private static final TypeReference<Map<String, String>> MAP_TYPE_REFERENCE = new TypeReference<>() {
    };

    private final HttpClient httpClient;
    private final BitvavoClientConfiguration configuration;

    public BitvavoHttpClient(BitvavoClientConfiguration configuration) {
        this.httpClient = newHttpClient();
        this.configuration = requireNonNull(configuration);
    }

    public BitvavoResponse<BitvavoTimeResponse> time() {
        verifyActionCanBeExecuted(1);

        URI uri = createURI("time")
            .build();

        return doRequest(createGETRequest(uri), BitvavoTimeResponse.class);
    }

    public BitvavoResponse<BitvavoMarketsResponse[]> markets() {
        verifyActionCanBeExecuted(1);

        URI uri = createURI("markets")
            .build();

        return doRequest(createGETRequest(uri), BitvavoMarketsResponse[].class);
    }

    public BitvavoResponse<BitvavoMarketsResponse> markets(BitvavoMarketsRequest request) {
        verifyActionCanBeExecuted(1);

        URI uri = createURI("markets")
            .withQueryParameters(convertToMap(request))
            .build();

        return doRequest(createGETRequest(uri), BitvavoMarketsResponse.class);
    }

    public BitvavoResponse<BitvavoAssetsResponse[]> assets() {
        verifyActionCanBeExecuted(1);

        URI uri = createURI("assets")
            .build();

        return doRequest(createGETRequest(uri), BitvavoAssetsResponse[].class);
    }

    public BitvavoResponse<BitvavoAssetsResponse> assets(BitvavoAssetsRequest request) {
        verifyActionCanBeExecuted(1);

        URI uri = createURI("assets")
            .withQueryParameters(convertToMap(request))
            .build();

        return doRequest(createGETRequest(uri), BitvavoAssetsResponse.class);
    }

    public BitvavoResponse<BitvavoMarketBookResponse> marketBook(BitvavoMarket market) {
        verifyActionCanBeExecuted(1);

        URI uri = createURI("{market}/book")
            .withPathVariable("market", market)
            .build();

        return doRequest(createGETRequest(uri), BitvavoMarketBookResponse.class);
    }

    public BitvavoResponse<BitvavoMarketBookResponse> marketBook(BitvavoMarket market, BitvavoMarketBookRequest request) {
        verifyActionCanBeExecuted(1);

        URI uri = createURI("{market}/book")
            .withPathVariable("market", market)
            .withQueryParameters(convertToMap(request))
            .build();

        return doRequest(createGETRequest(uri), BitvavoMarketBookResponse.class);
    }

    public BitvavoResponse<BitvavoMarketTradesResponse[]> marketTrades(BitvavoMarket market) {
        verifyActionCanBeExecuted(5);

        URI uri = createURI("{market}/trades")
            .withPathVariable("market", market)
            .build();

        return doRequest(createGETRequest(uri), BitvavoMarketTradesResponse[].class);
    }

    public BitvavoResponse<BitvavoMarketTradesResponse[]> marketTrades(BitvavoMarket market, BitvavoMarketTradesRequest request) {
        verifyActionCanBeExecuted(5);

        URI uri = createURI("{market}/trades")
            .withPathVariable("market", market)
            .withQueryParameters(convertToMap(request))
            .build();

        return doRequest(createGETRequest(uri), BitvavoMarketTradesResponse[].class);
    }

    public BitvavoResponse<BitvavoMarketCandlesResponse> marketCandles(BitvavoMarket market, BitvavoMarketCandlesRequest request) {
        verifyActionCanBeExecuted(1);

        URI uri = createURI("{market}/candles")
            .withPathVariable("market", market)
            .withQueryParameters(convertToMap(request))
            .build();

        return doRequest(createGETRequest(uri), BitvavoMarketCandlesResponse.class);
    }

    public BitvavoResponse<BitvavoTickerPriceResponse[]> tickerPrice() {
        verifyActionCanBeExecuted(1);

        URI uri = createURI("/ticker/price")
            .build();

        return doRequest(createGETRequest(uri), BitvavoTickerPriceResponse[].class);
    }

    public BitvavoResponse<BitvavoTickerPriceResponse> tickerPrice(BitvavoTickerPriceRequest request) {
        verifyActionCanBeExecuted(1);

        URI uri = createURI("/ticker/price")
            .withQueryParameters(convertToMap(request))
            .build();

        return doRequest(createGETRequest(uri), BitvavoTickerPriceResponse.class);
    }

    public BitvavoResponse<BitvavoTickerBookResponse[]> tickerBook() {
        verifyActionCanBeExecuted(1);

        URI uri = createURI("/ticker/book")
            .build();

        return doRequest(createGETRequest(uri), BitvavoTickerBookResponse[].class);
    }

    public BitvavoResponse<BitvavoTickerBookResponse> tickerBook(BitvavoTickerBookRequest request) {
        verifyActionCanBeExecuted(1);

        URI uri = createURI("/ticker/book")
            .withQueryParameters(convertToMap(request))
            .build();

        return doRequest(createGETRequest(uri), BitvavoTickerBookResponse.class);
    }

    public BitvavoResponse<BitvavoTicker24hResponse[]> ticker24h() {
        verifyActionCanBeExecuted(25);

        URI uri = createURI("/ticker/24h")
            .build();

        return doRequest(createGETRequest(uri), BitvavoTicker24hResponse[].class);
    }

    public BitvavoResponse<BitvavoTicker24hResponse> ticker24h(BitvavoTicker24hRequest request) {
        verifyActionCanBeExecuted(1);

        URI uri = createURI("/ticker/24h")
            .withQueryParameters(convertToMap(request))
            .build();

        return doRequest(createGETRequest(uri), BitvavoTicker24hResponse.class);
    }

    public BitvavoResponse<BitvavoOrderResponse> newOrder(BitvavoNewOrderRequest request) {
        verifyActionCanBeExecuted(1);

        URI uri = createURI("/order")
            .build();

        return doRequest(createPOSTRequest(uri, request), BitvavoOrderResponse.class);
    }

    public BitvavoResponse<BitvavoOrderResponse> updateOrder(BitvavoUpdateOrderRequest request) {
        verifyActionCanBeExecuted(1);

        URI uri = createURI("/order")
            .build();

        return doRequest(createPUTRequest(uri, request), BitvavoOrderResponse.class);
    }

    public BitvavoResponse<BitvavoCancelOrderResponse> cancelOrder(BitvavoCancelOrderRequest request) {
        verifyActionCanBeExecuted(1);

        URI uri = createURI("/order")
            .withQueryParameters(convertToMap(request))
            .build();

        return doRequest(createDELETERequest(uri), BitvavoCancelOrderResponse.class);
    }

    public BitvavoResponse<BitvavoOrderResponse> order(BitvavoOrderRequest request) {
        verifyActionCanBeExecuted(1);

        URI uri = createURI("/order")
            .withQueryParameters(convertToMap(request))
            .build();

        return doRequest(createGETRequest(uri), BitvavoOrderResponse.class);
    }

    public BitvavoResponse<BitvavoOrderResponse[]> orders(BitvavoOrdersRequest request) {
        verifyActionCanBeExecuted(5);

        URI uri = createURI("/orders")
            .withQueryParameters(convertToMap(request))
            .build();

        return doRequest(createGETRequest(uri), BitvavoOrderResponse[].class);
    }

    public BitvavoResponse<BitvavoCancelOrderResponse[]> cancelOrders(BitvavoCancelOrdersRequest request) {
        verifyActionCanBeExecuted(1);

        URI uri = createURI("/orders")
            .withQueryParameters(convertToMap(request))
            .build();

        return doRequest(createDELETERequest(uri), BitvavoCancelOrderResponse[].class);
    }

    public BitvavoResponse<BitvavoOrderResponse[]> ordersOpen() {
        verifyActionCanBeExecuted(25);

        URI uri = createURI("/ordersOpen")
            .build();

        return doRequest(createGETRequest(uri), BitvavoOrderResponse[].class);
    }

    public BitvavoResponse<BitvavoOrderResponse[]> ordersOpen(BitvavoOrdersOpenRequest request) {
        verifyActionCanBeExecuted(1);

        URI uri = createURI("/ordersOpen")
            .withQueryParameters(convertToMap(request))
            .build();

        return doRequest(createGETRequest(uri), BitvavoOrderResponse[].class);
    }

    public BitvavoResponse<BitvavoMarketTradesResponse[]> trades(BitvavoTradesRequest request) {
        verifyActionCanBeExecuted(5);

        URI uri = createURI("/trades")
            .withQueryParameters(convertToMap(request))
            .build();

        return doRequest(createGETRequest(uri), BitvavoMarketTradesResponse[].class);
    }

    public BitvavoResponse<BitvavoAccountResponse> account() {
        verifyActionCanBeExecuted(1);

        URI uri = createURI("/account")
            .build();

        return doRequest(createGETRequest(uri), BitvavoAccountResponse.class);
    }

    public BitvavoResponse<BitvavoBalanceResponse[]> balance() {
        verifyActionCanBeExecuted(5);

        URI uri = createURI("/balance")
            .build();

        return doRequest(createGETRequest(uri), BitvavoBalanceResponse[].class);
    }

    public BitvavoResponse<BitvavoBalanceResponse[]> balance(BitvavoBalanceRequest request) {
        verifyActionCanBeExecuted(5);

        URI uri = createURI("/balance")
            .withQueryParameters(convertToMap(request))
            .build();

        return doRequest(createGETRequest(uri), BitvavoBalanceResponse[].class);
    }

    public BitvavoResponse<BitvavoDepositResponse[]> deposit(BitvavoDepositRequest request) {
        verifyActionCanBeExecuted(1);

        URI uri = createURI("/deposit")
            .withQueryParameters(convertToMap(request))
            .build();

        return doRequest(createGETRequest(uri), BitvavoDepositResponse[].class);
    }

    public BitvavoResponse<BitvavoBankDepositResponse> bankDeposit(BitvavoDepositRequest request) {
        verifyActionCanBeExecuted(1);

        URI uri = createURI("/deposit")
            .withQueryParameters(convertToMap(request))
            .build();

        return doRequest(createGETRequest(uri), BitvavoBankDepositResponse.class);
    }

    public BitvavoResponse<BitvavoDigitalAssetDepositResponse> digitalAssetDeposit(BitvavoDepositRequest request) {
        verifyActionCanBeExecuted(1);

        URI uri = createURI("/deposit")
            .withQueryParameters(convertToMap(request))
            .build();

        return doRequest(createGETRequest(uri), BitvavoDigitalAssetDepositResponse.class);
    }

    public BitvavoResponse<BitvavoDepositHistoryResponse[]> depositHistory() {
        verifyActionCanBeExecuted(5);

        URI uri = createURI("/depositHistory")
            .build();

        return doRequest(createGETRequest(uri), BitvavoDepositHistoryResponse[].class);
    }

    public BitvavoResponse<BitvavoDepositHistoryResponse[]> depositHistory(BitvavoDepositHistoryRequest request) {
        verifyActionCanBeExecuted(5);

        URI uri = createURI("/depositHistory")
            .withQueryParameters(convertToMap(request))
            .build();

        return doRequest(createGETRequest(uri), BitvavoDepositHistoryResponse[].class);
    }

    public BitvavoResponse<BitvavoBankDepositHistoryResponse[]> bankDepositHistory(BitvavoDepositHistoryRequest request) {
        verifyActionCanBeExecuted(5);

        URI uri = createURI("/depositHistory")
            .withQueryParameters(convertToMap(request))
            .build();

        return doRequest(createGETRequest(uri), BitvavoBankDepositHistoryResponse[].class);
    }

    public BitvavoResponse<BitvavoDigitalAssetDepositResponse[]> digitalAssetDepositHistory(BitvavoDepositHistoryRequest request) {
        verifyActionCanBeExecuted(5);

        URI uri = createURI("/depositHistory")
            .withQueryParameters(convertToMap(request))
            .build();

        return doRequest(createGETRequest(uri), BitvavoDigitalAssetDepositResponse[].class);
    }

    public BitvavoResponse<BitvavoWithdrawalResponse> withdrawal(BitvavoWithdrawalRequest request) {
        verifyActionCanBeExecuted(1);

        URI uri = createURI("/withdrawal")
            .build();

        return doRequest(createPOSTRequest(uri, request), BitvavoWithdrawalResponse.class);
    }

    public BitvavoResponse<BitvavoWithdrawalHistoryResponse[]> withdrawalHistory() {
        verifyActionCanBeExecuted(5);

        URI uri = createURI("/withdrawalHistory")
            .build();

        return doRequest(createGETRequest(uri), BitvavoWithdrawalHistoryResponse[].class);
    }

    public BitvavoResponse<BitvavoWithdrawalHistoryResponse[]> withdrawalHistory(BitvavoWithdrawalHistoryRequest request) {
        verifyActionCanBeExecuted(5);

        URI uri = createURI("/withdrawalHistory")
            .withQueryParameters(convertToMap(request))
            .build();

        return doRequest(createGETRequest(uri), BitvavoWithdrawalHistoryResponse[].class);
    }

    private BitvavoHttpRequest createGETRequest(URI uri) {
        return new BitvavoHttpRequest.Builder()
            .withUri(uri)
            .withMethod("GET")
            .withConfiguration(configuration)
            .build();
    }

    private BitvavoHttpRequest createPOSTRequest(URI uri, Object body) {
        return new BitvavoHttpRequest.Builder()
            .withUri(uri)
            .withMethod("POST")
            .withConfiguration(configuration)
            .withBody(body)
            .build();
    }

    private BitvavoHttpRequest createPUTRequest(URI uri, Object body) {
        return new BitvavoHttpRequest.Builder()
            .withUri(uri)
            .withMethod("PUT")
            .withConfiguration(configuration)
            .withBody(body)
            .build();
    }

    private BitvavoHttpRequest createDELETERequest(URI uri) {
        return new BitvavoHttpRequest.Builder()
            .withUri(uri)
            .withMethod("DELETE")
            .withConfiguration(configuration)
            .build();
    }

    private <T> BitvavoResponse<T> doRequest(BitvavoHttpRequest request, Class<T> responseClassType) {
        try {
            BitvavoResponseParser<T> responseFactory = new BitvavoResponseParser.Builder<>(responseClassType)
                .withObjectMapper(getObjectMapper())
                .withRateLimiter(configuration.getRateLimiter())
                .build();

            HttpResponse<String> httpResponse = httpClient.send(request.asHttpRequest(), ofString());
            return responseFactory.parseResponse(httpResponse.body(), metadata(httpResponse.headers()));
        } catch (Exception exception) {
            return BitvavoResponse.error(exception);
        }
    }

    private void verifyActionCanBeExecuted(long actionWeight) {
        configuration.getRateLimiter().verifyActionCanBeExecuted(actionWeight);
    }

    private URIBuilder createURI(String apiPath) {
        return new URIBuilder(configuration.getRestUrl(), apiPath);
    }

    private Map<String, String> convertToMap(Object request) {
        try {
            return getObjectMapper().convertValue(request, MAP_TYPE_REFERENCE);
        } catch (Exception e) {
            throw new BitvavoClientException(e);
        }
    }

    private ObjectMapper getObjectMapper() {
        return configuration.getObjectMapper();
    }
}
