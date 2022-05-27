package be.davidopdebeeck.bitvavo.client.websocket.handler;

import java.util.HashMap;
import java.util.Map;

import static be.davidopdebeeck.bitvavo.client.websocket.handler.BitvavoWebsocketHandlerChain.emptyChain;

public class BitvavoWebsocketHandlerRegistry {

    private final Map<String, BitvavoWebsocketHandlerChain> handlersByMessageType;

    public BitvavoWebsocketHandlerRegistry() {
        this.handlersByMessageType = new HashMap<>();
    }

    public <T> void registerHandler(String messageType, BitvavoWebsocketHandler<T> handler) {
        handlersByMessageType.putIfAbsent(messageType, emptyChain());
        handlersByMessageType.get(messageType).register(handler);
    }

    public BitvavoWebsocketHandlerChain findHandlerChainBy(String eventName) {
        return handlersByMessageType.getOrDefault(eventName, emptyChain());
    }
}
