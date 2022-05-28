package be.davidopdebeeck.bitvavo.client.websocket.handler;

import java.util.HashMap;
import java.util.Map;

import static be.davidopdebeeck.bitvavo.client.websocket.handler.BitvavoWebsocketHandlerChain.emptyChain;

public class BitvavoWebsocketHandlerRegistry {

    private final Map<String, BitvavoWebsocketHandlerChain> handlersByEventName;

    public BitvavoWebsocketHandlerRegistry() {
        this.handlersByEventName = new HashMap<>();
    }

    public <T> void registerHandler(String eventName, BitvavoWebsocketHandler<T> handler) {
        handlersByEventName.putIfAbsent(eventName, emptyChain());
        handlersByEventName.get(eventName).register(handler);
    }

    public BitvavoWebsocketHandlerChain findHandlerChainBy(String eventName) {
        return handlersByEventName.getOrDefault(eventName, emptyChain());
    }
}
