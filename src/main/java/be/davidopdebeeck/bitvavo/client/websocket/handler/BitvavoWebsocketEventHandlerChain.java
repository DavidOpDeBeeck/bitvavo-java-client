package be.davidopdebeeck.bitvavo.client.websocket.handler;

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

    public void register(BitvavoWebsocketEventHandler<?> handler) {
        this.handlers.add(handler);
    }

    public synchronized void handle(String response) {
        handlers.forEach(handler -> handler.handle(response));
        handlers.removeIf(BitvavoWebsocketEventHandler::isOneTimeUse);
    }
}
