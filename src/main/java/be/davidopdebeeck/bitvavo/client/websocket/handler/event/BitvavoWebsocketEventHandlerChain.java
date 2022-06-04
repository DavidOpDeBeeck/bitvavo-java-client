package be.davidopdebeeck.bitvavo.client.websocket.handler.event;

import java.util.ArrayList;
import java.util.List;

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

    public synchronized void handle(String responseAsString) {
        handlers.forEach(handler -> handler.handle(responseAsString));
        handlers.removeIf(BitvavoWebsocketEventHandler::isOneTimeUse);
    }
}
