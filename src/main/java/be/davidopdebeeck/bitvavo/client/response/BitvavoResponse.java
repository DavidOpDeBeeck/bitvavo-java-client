package be.davidopdebeeck.bitvavo.client.response;


import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static be.davidopdebeeck.bitvavo.client.response.BitvavoErrorMessage.fromException;
import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class BitvavoResponse<T> {

    public static <T> BitvavoResponse<T> ok(T value) {
        return new BitvavoResponse<>(value);
    }

    public static <T> BitvavoResponse<T> error(Exception exception) {
        return new BitvavoResponse<>(fromException(exception));
    }

    public static <T> BitvavoResponse<T> error(BitvavoErrorMessage errorMessage) {
        return new BitvavoResponse<>(errorMessage);
    }

    private final T value;
    private final BitvavoErrorMessage errorMessage;

    private BitvavoResponse(T value) {
        this.value = requireNonNull(value);
        this.errorMessage = null;
    }

    private BitvavoResponse(BitvavoErrorMessage errorMessage) {
        this.value = null;
        this.errorMessage = requireNonNull(errorMessage);
    }

    public boolean hasErrors() {
        return errorMessage != null;
    }

    public boolean isOk() {
        return value != null;
    }

    public <U> BitvavoResponse<U> map(Function<T, U> mapper) {
        if (hasErrors()) {
            return error(errorMessage);
        }
        return ok(mapper.apply(value));
    }

    public void handle(Consumer<T> resultConsumer) {
        if (isOk()) {
            resultConsumer.accept(value);
        }
    }

    public void handle(Consumer<T> resultConsumer, Consumer<BitvavoErrorMessage> errorMessageConsumer) {
        if (isOk()) {
            resultConsumer.accept(value);
            return;
        }
        errorMessageConsumer.accept(errorMessage);
    }

    public T orElse(Supplier<T> function) {
        if (hasErrors()) {
            return function.get();
        }
        return value;
    }

    public T orElse(Function<BitvavoErrorMessage, T> function) {
        if (hasErrors()) {
            return function.apply(errorMessage);
        }
        return value;
    }

    public T getOrThrow() {
        if (hasErrors()) {
            throw new NoSuchElementException(format("No value present, but there was an error: (%s)", errorMessage));
        }
        return value;
    }

    public BitvavoErrorMessage getErrorMessage() {
        return errorMessage;
    }
}
