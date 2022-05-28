package be.davidopdebeeck.bitvavo.client.websocket.handler;

public interface BitvavoWebsocketErrorHandler {

    void handle(Throwable throwable);
}
