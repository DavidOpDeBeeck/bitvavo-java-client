package be.davidopdebeeck.bitvavo.client.websocket;

import be.davidopdebeeck.bitvavo.client.BitvavoClientConfiguration;
import be.davidopdebeeck.bitvavo.client.api.authenticate.BitvavoAuthenticateRequest;
import be.davidopdebeeck.bitvavo.client.api.authenticate.BitvavoAuthenticateResponse;
import be.davidopdebeeck.bitvavo.client.websocket.handler.BitvavoWebsocketEventHandler;
import be.davidopdebeeck.bitvavo.client.websocket.handler.BitvavoWebsocketEventHandlerRegistry;
import be.davidopdebeeck.bitvavo.client.websocket.request.BitvavoWebsocketRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import javax.websocket.*;

import static be.davidopdebeeck.bitvavo.client.utils.RetryUtils.retryUntilTrue;
import static be.davidopdebeeck.bitvavo.client.utils.SignatureUtils.createSignature;
import static java.lang.String.valueOf;
import static java.util.Objects.requireNonNull;

@ClientEndpoint
public class BitvavoWebsocketEndpoint {

    private static final String AUTHENTICATE = "authenticate";

    private final BitvavoClientConfiguration configuration;
    private final BitvavoWebsocketEventHandlerRegistry handlerRegistry;

    private Session session;
    private boolean authenticated = false;

    BitvavoWebsocketEndpoint(BitvavoClientConfiguration configuration) {
        this.configuration = requireNonNull(configuration);
        this.handlerRegistry = new BitvavoWebsocketEventHandlerRegistry();
    }

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        authenticate();
    }

    @OnMessage
    public void processMessage(String response) {
        System.out.println("processMessage: " + response);
        JsonNode responseNode = convertToJsonNode(response);

        JsonNode eventNode = responseNode.get("event");
        if (eventNode != null) {
            String eventName = eventNode.asText();
            handlerRegistry.findEventHandlerChainBy(eventName).handle(response);
        }

        JsonNode actionNode = responseNode.get("action");
        if (actionNode != null) {
            String actionName = actionNode.asText();
            String nestedResponse = responseNode.get("response").toString();
            handlerRegistry.findEventHandlerChainBy(actionName).handle(nestedResponse);
        }
    }

    @OnError
    public void processError(Throwable t) {
        t.printStackTrace();
    }

    public <T> void subscribe(String subscription, BitvavoWebsocketEventHandler<T> handler) {
        subscribe(subscription, null, handler);
    }

    public <T> void subscribe(String subscription, Object request, BitvavoWebsocketEventHandler<T> handler) {
        handlerRegistry.registerHandler(subscription, handler);
        doRequest(new BitvavoWebsocketRequest.Builder()
            .withAction("subscribe")
            .withRequest(request)
            .build());
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

    public void registerHandler(String eventName, BitvavoWebsocketEventHandler<?> handler) {
        handlerRegistry.registerHandler(eventName, handler);
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
            .withResponseHandler(response -> authenticated = response.asType().isAuthenticated())
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
