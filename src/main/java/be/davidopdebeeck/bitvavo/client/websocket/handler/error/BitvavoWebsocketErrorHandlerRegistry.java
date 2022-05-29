package be.davidopdebeeck.bitvavo.client.websocket.handler.error;

import static be.davidopdebeeck.bitvavo.client.websocket.handler.error.BitvavoWebsocketErrorHandlerChain.emptyChain;

public class BitvavoWebsocketErrorHandlerRegistry {

    private final BitvavoWebsocketErrorHandlerChain errorHandlerChain;

    public BitvavoWebsocketErrorHandlerRegistry() {
        this.errorHandlerChain = emptyChain();
    }

    public void registerHandler(BitvavoWebsocketErrorHandler handler) {
        errorHandlerChain.register(handler);
    }

    public BitvavoWebsocketErrorHandlerChain findErrorHandlerChain() {
        return errorHandlerChain;
    }
}
