package be.davidopdebeeck.bitvavo.client.websocket.handler;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.singletonList;

public class BitvavoWebsocketErrorHandlerRegistry {

    private static final DefaultLoggingErrorHandler DEFAULT_LOGGING_ERROR_HANDLER = new DefaultLoggingErrorHandler();

    private final List<BitvavoWebsocketErrorHandler> errorHandlers;

    public BitvavoWebsocketErrorHandlerRegistry() {
        this.errorHandlers = new ArrayList<>();
    }

    public void registerHandler(BitvavoWebsocketErrorHandler handler) {
        errorHandlers.add(handler);
    }

    public List<BitvavoWebsocketErrorHandler> findErrorHandlers() {
        if (errorHandlers.isEmpty()) {
            return singletonList(DEFAULT_LOGGING_ERROR_HANDLER);
        }
        return errorHandlers;
    }

}
