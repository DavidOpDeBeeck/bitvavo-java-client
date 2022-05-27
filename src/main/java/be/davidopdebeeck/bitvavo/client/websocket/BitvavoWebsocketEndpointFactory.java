package be.davidopdebeeck.bitvavo.client.websocket;

import be.davidopdebeeck.bitvavo.client.BitvavoClientConfiguration;

import javax.websocket.ContainerProvider;
import javax.websocket.WebSocketContainer;
import java.net.URI;

import static be.davidopdebeeck.bitvavo.client.utils.RetryUtils.retry;

public class BitvavoWebsocketEndpointFactory {

    public static BitvavoWebsocketEndpoint createWebsocketEndpoint(BitvavoClientConfiguration configuration) {
        return retry(() -> {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            BitvavoWebsocketEndpoint endpoint = new BitvavoWebsocketEndpoint(configuration);
            container.connectToServer(endpoint, URI.create(configuration.getWsUrl()));
            return endpoint;
        }, "Failed to initialize websocket endpoint.");
    }

}
