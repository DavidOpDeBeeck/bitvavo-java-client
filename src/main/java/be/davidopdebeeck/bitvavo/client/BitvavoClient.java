package be.davidopdebeeck.bitvavo.client;

import be.davidopdebeeck.bitvavo.client.http.BitvavoHttpClient;
import be.davidopdebeeck.bitvavo.client.websocket.BitvavoWebsocketClient;

public class BitvavoClient {

    private final BitvavoHttpClient httpClient;
    private final BitvavoWebsocketClient websocketClient;

    public BitvavoClient(BitvavoClientConfiguration configuration) {
        this.httpClient = new BitvavoHttpClient(configuration);
        this.websocketClient = new BitvavoWebsocketClient(configuration);
    }

    public BitvavoHttpClient httpClient() {
        return httpClient;
    }

    public BitvavoWebsocketClient websocketClient() {
        return websocketClient;
    }
}
