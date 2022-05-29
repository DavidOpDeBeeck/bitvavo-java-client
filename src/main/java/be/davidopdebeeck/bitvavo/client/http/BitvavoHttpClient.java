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
import be.davidopdebeeck.bitvavo.client.api.ticker24h.BitvavoTicker24hRequest;
import be.davidopdebeeck.bitvavo.client.api.ticker24h.BitvavoTicker24hResponse;
import be.davidopdebeeck.bitvavo.client.api.tickerbook.BitvavoTickerBookRequest;
import be.davidopdebeeck.bitvavo.client.api.tickerbook.BitvavoTickerBookResponse;
import be.davidopdebeeck.bitvavo.client.api.tickerprice.BitvavoTickerPriceRequest;
import be.davidopdebeeck.bitvavo.client.api.tickerprice.BitvavoTickerPriceResponse;
import be.davidopdebeeck.bitvavo.client.api.time.BitvavoTimeResponse;
import be.davidopdebeeck.bitvavo.client.api.trades.BitvavoTradesRequest;
import be.davidopdebeeck.bitvavo.client.api.withdrawalhistory.BitvavoWithdrawalHistoryRequest;
import be.davidopdebeeck.bitvavo.client.api.withdrawalhistory.BitvavoWithdrawalHistoryResponse;
import be.davidopdebeeck.bitvavo.client.http.request.BitvavoHttpRequest;
import be.davidopdebeeck.bitvavo.client.http.response.BitvavoHttpResponse;
import be.davidopdebeeck.bitvavo.client.utils.URIBuilder;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.util.Map;

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

    public BitvavoHttpResponse<BitvavoTimeResponse> time() {
        URI uri = createURI("time")
            .build();

        return doRequest(createGETRequest(uri), BitvavoTimeResponse.class);
    }

    public BitvavoHttpResponse<BitvavoMarketsResponse[]> markets() {
        URI uri = createURI("markets")
            .build();

        return doRequest(createGETRequest(uri), BitvavoMarketsResponse[].class);
    }

    public BitvavoHttpResponse<BitvavoMarketsResponse> markets(BitvavoMarketsRequest request) {
        URI uri = createURI("markets")
            .withQueryParameters(convertToMap(request))
            .build();

        return doRequest(createGETRequest(uri), BitvavoMarketsResponse.class);
    }

    public BitvavoHttpResponse<BitvavoAssetsResponse[]> assets() {
        URI uri = createURI("assets")
            .build();

        return doRequest(createGETRequest(uri), BitvavoAssetsResponse[].class);
    }

    public BitvavoHttpResponse<BitvavoAssetsResponse> assets(BitvavoAssetsRequest request) {
        URI uri = createURI("assets")
            .withQueryParameters(convertToMap(request))
            .build();

        return doRequest(createGETRequest(uri), BitvavoAssetsResponse.class);
    }

    public BitvavoHttpResponse<BitvavoMarketBookResponse> marketBook(BitvavoMarket market) {
        URI uri = createURI("{market}/book")
            .withPathVariable("market", market)
            .build();

        return doRequest(createGETRequest(uri), BitvavoMarketBookResponse.class);
    }

    public BitvavoHttpResponse<BitvavoMarketBookResponse> marketBook(BitvavoMarket market, BitvavoMarketBookRequest request) {
        URI uri = createURI("{market}/book")
            .withPathVariable("market", market)
            .withQueryParameters(convertToMap(request))
            .build();

        return doRequest(createGETRequest(uri), BitvavoMarketBookResponse.class);
    }

    public BitvavoHttpResponse<BitvavoMarketTradesResponse[]> marketTrades(BitvavoMarket market) {
        URI uri = createURI("{market}/trades")
            .withPathVariable("market", market)
            .build();

        return doRequest(createGETRequest(uri), BitvavoMarketTradesResponse[].class);
    }

    public BitvavoHttpResponse<BitvavoMarketTradesResponse[]> marketTrades(BitvavoMarket market, BitvavoMarketTradesRequest request) {
        URI uri = createURI("{market}/trades")
            .withPathVariable("market", market)
            .withQueryParameters(convertToMap(request))
            .build();

        return doRequest(createGETRequest(uri), BitvavoMarketTradesResponse[].class);
    }

    public BitvavoHttpResponse<BitvavoMarketCandlesResponse> marketCandles(BitvavoMarket market, BitvavoMarketCandlesRequest request) {
        URI uri = createURI("{market}/candles")
            .withPathVariable("market", market)
            .withQueryParameters(convertToMap(request))
            .build();

        return doRequest(createGETRequest(uri), BitvavoMarketCandlesResponse.class);
    }

    public BitvavoHttpResponse<BitvavoTickerPriceResponse[]> tickerPrice() {
        URI uri = createURI("/ticker/price")
            .build();

        return doRequest(createGETRequest(uri), BitvavoTickerPriceResponse[].class);
    }

    public BitvavoHttpResponse<BitvavoTickerPriceResponse> tickerPrice(BitvavoTickerPriceRequest request) {
        URI uri = createURI("/ticker/price")
            .withQueryParameters(convertToMap(request))
            .build();

        return doRequest(createGETRequest(uri), BitvavoTickerPriceResponse.class);
    }

    public BitvavoHttpResponse<BitvavoTickerBookResponse[]> tickerBook() {
        URI uri = createURI("/ticker/book")
            .build();

        return doRequest(createGETRequest(uri), BitvavoTickerBookResponse[].class);
    }

    public BitvavoHttpResponse<BitvavoTickerBookResponse> tickerBook(BitvavoTickerBookRequest request) {
        URI uri = createURI("/ticker/book")
            .withQueryParameters(convertToMap(request))
            .build();

        return doRequest(createGETRequest(uri), BitvavoTickerBookResponse.class);
    }

    public BitvavoHttpResponse<BitvavoTicker24hResponse[]> ticker24h() {
        URI uri = createURI("/ticker/24h")
            .build();

        return doRequest(createGETRequest(uri), BitvavoTicker24hResponse[].class);
    }

    public BitvavoHttpResponse<BitvavoTicker24hResponse> ticker24h(BitvavoTicker24hRequest request) {
        URI uri = createURI("/ticker/24h")
            .withQueryParameters(convertToMap(request))
            .build();

        return doRequest(createGETRequest(uri), BitvavoTicker24hResponse.class);
    }

    public BitvavoHttpResponse<BitvavoMarketTradesResponse> trades(BitvavoTradesRequest request) {
        URI uri = createURI("/trades")
            .withQueryParameters(convertToMap(request))
            .build();

        return doRequest(createGETRequest(uri), BitvavoMarketTradesResponse.class);
    }

    public BitvavoHttpResponse<BitvavoAccountResponse> account() {
        URI uri = createURI("/account")
            .build();

        return doRequest(createGETRequest(uri), BitvavoAccountResponse.class);
    }

    public BitvavoHttpResponse<BitvavoBalanceResponse[]> balance() {
        URI uri = createURI("/balance")
            .build();

        return doRequest(createGETRequest(uri), BitvavoBalanceResponse[].class);
    }

    public BitvavoHttpResponse<BitvavoBalanceResponse> balance(BitvavoBalanceRequest request) {
        URI uri = createURI("/balance")
            .withQueryParameters(convertToMap(request))
            .build();

        return doRequest(createGETRequest(uri), BitvavoBalanceResponse.class);
    }

    public BitvavoHttpResponse<BitvavoDepositResponse> deposit(BitvavoDepositRequest request) {
        URI uri = createURI("/deposit")
            .withQueryParameters(convertToMap(request))
            .build();

        return doRequest(createGETRequest(uri), BitvavoDepositResponse.class);
    }

    public BitvavoHttpResponse<BitvavoBankDepositResponse> bankDeposit(BitvavoDepositRequest request) {
        URI uri = createURI("/deposit")
            .withQueryParameters(convertToMap(request))
            .build();

        return doRequest(createGETRequest(uri), BitvavoBankDepositResponse.class);
    }

    public BitvavoHttpResponse<BitvavoDigitalAssetDepositResponse> digitalAssetDeposit(BitvavoDepositRequest request) {
        URI uri = createURI("/deposit")
            .withQueryParameters(convertToMap(request))
            .build();

        return doRequest(createGETRequest(uri), BitvavoDigitalAssetDepositResponse.class);
    }

    public BitvavoHttpResponse<BitvavoDepositHistoryResponse[]> depositHistory() {
        URI uri = createURI("/depositHistory")
            .build();

        return doRequest(createGETRequest(uri), BitvavoDepositHistoryResponse[].class);
    }

    public BitvavoHttpResponse<BitvavoDepositHistoryResponse[]> depositHistory(BitvavoDepositHistoryRequest request) {
        URI uri = createURI("/depositHistory")
            .withQueryParameters(convertToMap(request))
            .build();

        return doRequest(createGETRequest(uri), BitvavoDepositHistoryResponse[].class);
    }

    public BitvavoHttpResponse<BitvavoBankDepositHistoryResponse[]> bankDeposit(BitvavoDepositHistoryRequest request) {
        URI uri = createURI("/depositHistory")
            .withQueryParameters(convertToMap(request))
            .build();

        return doRequest(createGETRequest(uri), BitvavoBankDepositHistoryResponse[].class);
    }

    public BitvavoHttpResponse<BitvavoBankDepositHistoryResponse[]> digitalAssetDeposit(BitvavoDepositHistoryRequest request) {
        URI uri = createURI("/depositHistory")
            .withQueryParameters(convertToMap(request))
            .build();

        return doRequest(createGETRequest(uri), BitvavoBankDepositHistoryResponse[].class);
    }

    public BitvavoHttpResponse<BitvavoWithdrawalHistoryResponse[]> withdrawalHistory() {
        URI uri = createURI("/withdrawalHistory")
            .build();

        return doRequest(createGETRequest(uri), BitvavoWithdrawalHistoryResponse[].class);
    }

    public BitvavoHttpResponse<BitvavoWithdrawalHistoryResponse[]> withdrawalHistory(BitvavoWithdrawalHistoryRequest request) {
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

    private <T> BitvavoHttpResponse<T> doRequest(BitvavoHttpRequest request, Class<T> responseClassType) {
        try {
            return new BitvavoHttpResponse.Builder<>(responseClassType)
                .withObjectMapper(getObjectMapper())
                .withResponse(httpClient.send(request.asHttpRequest(), ofString()))
                .build();
        } catch (Exception exception) {
            throw new BitvavoClientException(exception);
        }
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
