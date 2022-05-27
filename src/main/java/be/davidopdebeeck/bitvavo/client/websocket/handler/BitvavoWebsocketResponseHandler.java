package be.davidopdebeeck.bitvavo.client.websocket.handler;

import be.davidopdebeeck.bitvavo.client.websocket.response.BitvavoWebsocketResponse;

public interface BitvavoWebsocketResponseHandler<T> {

    void handle(BitvavoWebsocketResponse<T> response);
}
