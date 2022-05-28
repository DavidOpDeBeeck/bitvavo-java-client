# Bitvavo Java client

This a java client for the API that Bitvavo provides. Bitvavo also provides an API that you can
find [here](https://github.com/bitvavo/java-bitvavo-api), but this client provides specific request and response classes
to enhance the developer experience.

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
    * [ ] New order
    * [ ] Update order
    * [ ] Cancel order
    * [ ] Get order
    * [ ] Get orders
    * [ ] Cancel orders
    * [ ] Get open orders
* Trades
    * [x] Get trades
* Account
    * [x] Account
    * [x] Balance
    * [x] Deposit assets
    * [x] Deposit history
    * [ ] Withdraw assets
    * [ ] Withdrawal history
* Subscriptions
    * [x] Ticker subscription
    * [x] Ticker 24 hour subscription
    * [x] Account subscription
    * [ ] Candles subscription
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
