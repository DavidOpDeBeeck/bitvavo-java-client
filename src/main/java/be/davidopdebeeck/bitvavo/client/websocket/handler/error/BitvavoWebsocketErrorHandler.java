package be.davidopdebeeck.bitvavo.client.websocket.handler.error;

public interface BitvavoWebsocketErrorHandler {

    void handle(Throwable throwable);

    void handle(String errorCode, String errorMessage);
}
