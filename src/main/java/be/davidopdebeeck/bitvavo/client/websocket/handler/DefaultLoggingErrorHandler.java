package be.davidopdebeeck.bitvavo.client.websocket.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class DefaultLoggingErrorHandler implements BitvavoWebsocketErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultLoggingErrorHandler.class);

    @Override
    public void handle(Throwable throwable) {
        LOGGER.error("Something went wrong with the websocket connection or the message handling. " +
            "Use the provided stacktrace to determine the root cause. " +
            "To get rid of this message register your own error handler using the BitvavoWebsocketClient.errorHandler() method.", throwable);
    }
}
