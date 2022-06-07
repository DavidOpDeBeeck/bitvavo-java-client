# Bitvavo Java client

This a java client for the API that Bitvavo provides. Bitvavo also provides an API that you can
find [here](https://github.com/bitvavo/java-bitvavo-api), but this client provides specific request and response classes
to enhance the developer experience.

### BitvavoClient

The main entrypoint of this library is the `BitvavoClient` class. This class contains no logic, but simple creates the
underlying `BitvavoHttpClient` and `BitvavoWebsocketClient` clients needed to call the API. If you only need
the `BitvavoHttpClient` or the `BitvavoWebsocketClient` you can simply create those clients individually.

The `BitvavoClientConfiguration` class is used to configure the different clients. You can create an `apiKey`
and `apiSecret` on the [Bitvavo website](https://account.bitvavo.com/user/api).

```java
BitvavoClientConfiguration configuration=new BitvavoClientConfiguration.Builder()
    .withApiKey("<apiKey>")
    .withApiSecret("<apiSecret>")
    .withRestUrl("https://api.bitvavo.com/v2/")
    .withWsUrl("wss://ws.bitvavo.com/v2/")
    .withAccessWindow(10000)
    .build();


    BitvavoClient client=new BitvavoClient(configuration);
    BitvavoHttpClient httpClient=client.httpClient();
    BitvavoWebsocketClient websocketClient=client.websocketClient();
```

### BitvavoResponse

The response of an API call is always wrapped in a `BitvavoResponse` class. This class contains a result or an error
message if something went wrong. This also ensures that you "the developer" take into account that an API call can fail
and if it fails what should happen.

**Successful Response**

```java
BitvavoResponse<String> successfulResponse=BitvavoResponse.ok("RESULT");

System.out.println(successfulResponse.getOrThrow());
// RESULT
System.out.println(successfulResponse.map((result)->"SUCCESSFUL_"+result).getOrThrow());
// SUCCESSFUL_RESULT
System.out.println(successfulResponse.orElse(()->"FAILED_RESULT"));
// RESULT
System.out.println(successfulResponse.orElse((error)->error.getErrorMessage()));
// RESULT
successfulResponse.onResult(result->System.out.println(result));
// RESULT
successfulResponse.onError(error->System.out.println(error));
// 
successfulResponse.handle(result->System.out.println(result),error->System.out.println(error));
// RESULT
```

**Failed Response**

```java
BitvavoResponse<String> failedResponse=BitvavoResponse.error(new BitvavoErrorMessage.Builder()
    .withErrorCode("101")
    .withErrorMessage("Something went wrong!")
    .build());

System.out.println(failedResponse.getOrThrow());
// Exception in thread "main" java.util.NoSuchElementException: No value present, but there was an error: (101: Something went wrong!)
//	 at be.davidopdebeeck.bitvavo.client.response.BitvavoResponse.getOrThrow(BitvavoResponse.java:85)
System.out.println(failedResponse.orElse(()->"FAILED_RESULT"));
// FAILED_RESULT
System.out.println(failedResponse.orElse((error)->error.getErrorMessage()));
// Something went wrong!
failedResponse.onResult(result->System.out.println(result));
// 
failedResponse.onError(error->System.out.println(error));
// 101: Something went wrong!
failedResponse.handle(result->System.out.println(result),error->System.out.println(error));
// 101: Something went wrong!
```

### TODOs

* [x] Implement all endpoints
    * [x] for the BitvavoHttpClient
    * [x] for the BitvavoWebsocketClient
* [x] Implement rate limiting
    * [x] for the BitvavoHttpClient
    * [x] for the BitvavoWebsocketClient
* [ ] Add examples for endpoints
    * [ ] for the BitvavoHttpClient
    * [ ] for the BitvavoWebsocketClient

### Endpoints

* General
    * [x] Time
    * [x] Markets
    * [x] Assets
* Market Data
    * [x] Orderbook
    * [x] Trades
    * [x] Candles
    * [x] Ticker Price
    * [x] Ticker Book
    * [x] Ticker 24h
* Orders
    * [x] New order
    * [x] Update order
    * [x] Cancel order
    * [x] Get order
    * [x] Get orders
    * [x] Cancel orders
    * [x] Get open orders
* Trades
    * [x] Get trades
* Account
    * [x] Account
    * [x] Balance
    * [x] Deposit assets
    * [x] Deposit history
    * [x] Withdraw assets
    * [x] Withdrawal history
* Subscriptions
    * [x] Ticker subscription
    * [x] Ticker 24 hour subscription
    * [x] Account subscription
    * [x] Candles subscription
    * [x] Trades subscription
    * [x] Book subscription

### Conventions

#### Class naming

All classes that are exposed as part of the API start with the `Bitvavo` prefix.

#### BitvavoRequest classes

**Fields**

* All fields should be marked as `final`.
* All required fields need to be validated in the constructor using the `requireNonNull()` method.
* All optional fields need to have an optional getter method.

**Other**

* A nested Builder class should be provided for consistent object creation.

#### BitvavoResponse classes

**Fields**

* No fields should be marked as `final`.
* All required fields need to be validated in the constructor using the `requireNonNull()` method.
* All optional fields need to have an optional getter method.

**Other**

* An empty constructor should be provided for Jackson.
* A nested Builder class should be provided for consistent object creation.

#### BitvavoClient

**GET endpoints**

1. The method on the client has the same value as the name of the REST endpoint.
    * When the endpoint contains a path variable, then it is included in the naming.
    * When the endpoint contains a nested path, then all parts are combined using camelCase naming convention.
2. The query parameters for the REST endpoint are conveyed with a `Bitvavo(pathVariable)(endpointName)Request` parameter
   object.
3. The path variables for the REST endpoint are conveyed with a parameter object.
4. The response of the REST endpoint is conveyed with a `Bitvavo(pathVariable)(endpointName)Response` object.

**POST / PUT / DELETE endpoints**

1. The method on the client has the same value as the **action** of the REST endpoint.
    * When the endpoint contains a path variable, then it is included in the naming.
    * When the endpoint contains a nested path, then all parts are combined using camelCase naming convention.
2. The query parameters for the REST endpoint are conveyed with a `Bitvavo(pathVariable)(endpointName)Request` parameter
   object.
3. The request body for the REST endpoint are conveyed with a `Bitvavo(pathVariable)(endpointName)Request` parameter
   object.
3. The path variables for the REST endpoint are conveyed with a parameter object.
4. The response of the REST endpoint is conveyed with a `Bitvavo(pathVariable)(endpointName)Response` object.
