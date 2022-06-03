package be.davidopdebeeck.bitvavo.client.websocket.handler.error;

import be.davidopdebeeck.bitvavo.client.response.BitvavoErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class DefaultLoggingErrorHandler implements BitvavoWebsocketErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultLoggingErrorHandler.class);

    @Override
    public void handle(BitvavoErrorMessage errorMessage) {
        LOGGER.error("Something went wrong with the websocket connection or the message handling: {}", errorMessage);
    }
}
