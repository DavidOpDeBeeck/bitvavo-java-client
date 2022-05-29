package be.davidopdebeeck.bitvavo.client.websocket.handler.event;

import java.util.HashMap;
import java.util.Map;

import static be.davidopdebeeck.bitvavo.client.websocket.handler.event.BitvavoWebsocketEventHandlerChain.emptyChain;

public class BitvavoWebsocketEventHandlerRegistry {

    private final Map<String, BitvavoWebsocketEventHandlerChain> eventHandlersByEventName;

    public BitvavoWebsocketEventHandlerRegistry() {
        this.eventHandlersByEventName = new HashMap<>();
    }

    public <T> void registerHandler(String eventName, BitvavoWebsocketEventHandler<T> handler) {
        eventHandlersByEventName.putIfAbsent(eventName, emptyChain());
        eventHandlersByEventName.get(eventName).register(handler);
    }

    public BitvavoWebsocketEventHandlerChain findEventHandlerChainBy(String eventName) {
        return eventHandlersByEventName.getOrDefault(eventName, emptyChain());
    }
}
