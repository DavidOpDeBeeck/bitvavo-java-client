package be.davidopdebeeck.bitvavo.client.websocket.response;

public interface BitvavoWebsocketResponseHandler<T> {

    void handle(BitvavoWebsocketResponse<T> response);
}
