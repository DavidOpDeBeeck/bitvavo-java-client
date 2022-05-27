package be.davidopdebeeck.bitvavo.client.websocket.handler;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class BitvavoWebsocketHandlerChain {

    public static BitvavoWebsocketHandlerChain emptyChain() {
        return new BitvavoWebsocketHandlerChain();
    }

    private final List<BitvavoWebsocketHandler<?>> handlers;

    private BitvavoWebsocketHandlerChain() {
        this.handlers = new ArrayList<>();
    }

    public void register(BitvavoWebsocketHandler<?> handler) {
        this.handlers.add(handler);
    }

    public synchronized void handle(String response) {
        handlers.forEach(handler -> handler.handle(response));
        removeOneTimeUseHandlers();
    }

    private void removeOneTimeUseHandlers() {
        List<BitvavoWebsocketHandler<?>> oneTimeUseHandlers = handlers.stream()
            .filter(BitvavoWebsocketHandler::isOneTimeUse)
            .collect(toList());
        handlers.removeAll(oneTimeUseHandlers);
    }
}
