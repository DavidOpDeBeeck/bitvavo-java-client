package be.davidopdebeeck.bitvavo.client.websocket.handler.error;

import java.util.ArrayList;
import java.util.List;

public class BitvavoWebsocketErrorHandlerChain {

    private static final DefaultLoggingErrorHandler DEFAULT_LOGGING_ERROR_HANDLER = new DefaultLoggingErrorHandler();

    public static BitvavoWebsocketErrorHandlerChain emptyChain() {
        return new BitvavoWebsocketErrorHandlerChain();
    }

    private final List<BitvavoWebsocketErrorHandler> handlers;

    private BitvavoWebsocketErrorHandlerChain() {
        this.handlers = new ArrayList<>();
    }

    void register(BitvavoWebsocketErrorHandler handler) {
        this.handlers.add(handler);
    }

    public void handle(Throwable throwable) {
        if (handlers.isEmpty()) {
            DEFAULT_LOGGING_ERROR_HANDLER.handle(throwable);
        } else {
            handlers.forEach(handler -> handler.handle(throwable));
        }
    }

    public void handle(String errorCode, String errorMessage) {
        if (handlers.isEmpty()) {
            DEFAULT_LOGGING_ERROR_HANDLER.handle(errorCode, errorMessage);
        } else {
            handlers.forEach(handler -> handler.handle(errorCode, errorMessage));
        }
    }
}
