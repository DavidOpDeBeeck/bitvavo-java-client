package be.davidopdebeeck.bitvavo.client.websocket.handler.error;

import be.davidopdebeeck.bitvavo.client.response.BitvavoErrorMessage;

public interface BitvavoWebsocketErrorHandler {

    void handle(BitvavoErrorMessage errorMessage);
}
