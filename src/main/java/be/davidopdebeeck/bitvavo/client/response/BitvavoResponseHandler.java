package be.davidopdebeeck.bitvavo.client.response;

public interface BitvavoResponseHandler<T> {

    void handle(BitvavoResponse<T> response);
}
