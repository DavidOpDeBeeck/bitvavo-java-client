package be.davidopdebeeck.bitvavo.client.websocket;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.Map;

public class BitvavoWebsocketMessage {

    private String event;
    private String action;
    private Map<String, Object> response = new HashMap<>();
    private Map<String, Object> otherFields = new HashMap<>();

    public String getMessageIdentifier() {
        if (event != null) {
            return event;
        }
        return action;
    }

    public Map<String, Object> getMessageBody() {
        if (event != null) {
            return otherFields;
        }
        return response;
    }

    @JsonAnySetter
    public void setOtherField(String name, Object value) {
        otherFields.put(name, value);
    }
}
