package be.davidopdebeeck.bitvavo.client.websocket;

import be.davidopdebeeck.bitvavo.client.BitvavoClientConfiguration;
import be.davidopdebeeck.bitvavo.client.api.authenticate.BitvavoAuthenticateRequest;
import be.davidopdebeeck.bitvavo.client.api.authenticate.BitvavoAuthenticateResponse;
import be.davidopdebeeck.bitvavo.client.response.BitvavoErrorMessage;
import be.davidopdebeeck.bitvavo.client.response.BitvavoResponseParser;
import be.davidopdebeeck.bitvavo.client.websocket.handler.error.BitvavoWebsocketErrorHandler;
import be.davidopdebeeck.bitvavo.client.websocket.handler.error.BitvavoWebsocketErrorHandlerRegistry;
import be.davidopdebeeck.bitvavo.client.websocket.handler.event.BitvavoWebsocketEventHandler;
import be.davidopdebeeck.bitvavo.client.websocket.handler.event.BitvavoWebsocketEventHandlerRegistry;
import be.davidopdebeeck.bitvavo.client.websocket.request.BitvavoWebsocketRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;

import static be.davidopdebeeck.bitvavo.client.response.BitvavoErrorMessage.fromException;
import static be.davidopdebeeck.bitvavo.client.utils.RetryUtils.retryUntilTrue;
import static be.davidopdebeeck.bitvavo.client.utils.SignatureUtils.createSignature;
import static java.lang.String.valueOf;
import static java.util.Objects.requireNonNull;

@ClientEndpoint
public class BitvavoWebsocketEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(BitvavoWebsocketEndpoint.class);

    private static final String AUTHENTICATE = "authenticate";

    private final BitvavoClientConfiguration configuration;
    private final BitvavoWebsocketEventHandlerRegistry eventHandlerRegistry;
    private final BitvavoWebsocketErrorHandlerRegistry errorHandlerRegistry;
    private final BitvavoResponseParser<BitvavoWebsocketMessage> responseFactory;

    private Session session;
    private boolean authenticated = false;

    BitvavoWebsocketEndpoint(BitvavoClientConfiguration configuration) {
        this.configuration = requireNonNull(configuration);
        this.eventHandlerRegistry = new BitvavoWebsocketEventHandlerRegistry();
        this.errorHandlerRegistry = new BitvavoWebsocketErrorHandlerRegistry();
        this.responseFactory = new BitvavoResponseParser.Builder<>(BitvavoWebsocketMessage.class)
            .withObjectMapper(configuration.getObjectMapper())
            .build();
    }

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        authenticate();
    }

    @OnMessage
    public void processMessage(String response) {
        LOGGER.debug("Received websocket message: {}", response);

        responseFactory.parseResponse(response)
            .handle(this::handleResult, this::handleError);
    }

    private void handleResult(BitvavoWebsocketMessage message) {
        eventHandlerRegistry.findEventHandlerChainBy(message.getMessageIdentifier())
            .handle(message.getMessageBody());
    }

    private void handleError(BitvavoErrorMessage errorMessage) {
        errorHandlerRegistry.findErrorHandlerChain().handle(errorMessage);
    }

    @OnError
    public void processError(Throwable throwable) {
        errorHandlerRegistry.findErrorHandlerChain().handle(fromException(throwable));
    }

    public void doRequest(BitvavoWebsocketRequest websocketRequest) {
        try {
            verifyWebsocketIsOpen();
            verifySessionIsAuthentication(websocketRequest.getAction());

            String requestAsString = configuration.getObjectMapper().writeValueAsString(websocketRequest);
            this.session.getBasicRemote().sendText(requestAsString);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void registerHandler(BitvavoWebsocketErrorHandler handler) {
        errorHandlerRegistry.registerHandler(handler);
    }

    public void registerHandler(String eventName, BitvavoWebsocketEventHandler<?> handler) {
        eventHandlerRegistry.registerHandler(eventName, handler);
    }

    private JsonNode convertToJsonNode(String response) {
        try {
            return configuration.getObjectMapper().readTree(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void authenticate() {
        long currentTimeMillis = System.currentTimeMillis();
        BitvavoAuthenticateRequest request = new BitvavoAuthenticateRequest.Builder()
            .withKey(configuration.getApiKey())
            .withSignature(createSignature("/v2/websocket", "GET", currentTimeMillis, configuration.getApiSecret()))
            .withTimestamp(valueOf(currentTimeMillis))
            .withWindow(valueOf(configuration.getAccessWindow()))
            .build();

        registerHandler(AUTHENTICATE, new BitvavoWebsocketEventHandler.Builder<>(BitvavoAuthenticateResponse.class)
            .withOneTimeUse(true)
            .withObjectMapper(configuration.getObjectMapper())
            .withResponseHandler(response -> authenticated = response
                .map(BitvavoAuthenticateResponse::isAuthenticated)
                .orElse(error -> false))
            .build());

        doRequest(new BitvavoWebsocketRequest.Builder()
            .withAction(AUTHENTICATE)
            .withRequest(request)
            .build());
    }

    private void verifyWebsocketIsOpen() {
        retryUntilTrue(() -> this.session != null, "Waiting for session to be available.");
    }

    private void verifySessionIsAuthentication(String action) {
        retryUntilTrue(() -> this.authenticated || "authenticate".equals(action), "Waiting for session to be authenticated.");
    }
}
