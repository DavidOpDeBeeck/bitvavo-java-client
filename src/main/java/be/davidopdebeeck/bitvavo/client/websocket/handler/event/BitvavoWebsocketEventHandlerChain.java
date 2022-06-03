package be.davidopdebeeck.bitvavo.client.websocket.handler.event;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BitvavoWebsocketEventHandlerChain {

    public static BitvavoWebsocketEventHandlerChain emptyChain() {
        return new BitvavoWebsocketEventHandlerChain();
    }

    private final List<BitvavoWebsocketEventHandler<?>> handlers;

    private BitvavoWebsocketEventHandlerChain() {
        this.handlers = new ArrayList<>();
    }

    void register(BitvavoWebsocketEventHandler<?> handler) {
        this.handlers.add(handler);
    }

    public synchronized void handle(Map<String, Object> responseAsMap) {
        handlers.forEach(handler -> handler.handle(responseAsMap));
        handlers.removeIf(BitvavoWebsocketEventHandler::isOneTimeUse);
    }
}
