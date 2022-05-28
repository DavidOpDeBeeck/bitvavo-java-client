package be.davidopdebeeck.bitvavo.client;

// TODO: replace RuntimeException with BitvavoClientException when appropriate
public class BitvavoClientException extends RuntimeException {

    public BitvavoClientException(Throwable cause) {
        super(cause);
    }
}
