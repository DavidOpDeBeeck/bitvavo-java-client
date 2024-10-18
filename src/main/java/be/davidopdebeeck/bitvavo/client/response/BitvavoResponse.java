package be.davidopdebeeck.bitvavo.client.response;


import java.util.NoSuchElementException;
import java.util.function.Function;

import static be.davidopdebeeck.bitvavo.client.response.BitvavoErrorMessage.fromException;
import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public sealed interface BitvavoResponse<T> {

    static <T> BitvavoResponse<T> ok(T value) {
        return new Ok<>(value);
    }

    static <T> BitvavoResponse<T> error(Exception exception) {
        return new Error<>(fromException(exception));
    }

    static <T> BitvavoResponse<T> error(BitvavoErrorMessage errorMessage) {
        return new Error<>(errorMessage);
    }

    <U> BitvavoResponse<U> map(Function<T, U> mapper);

    T getOrThrow();

    record Ok<T>(T value) implements BitvavoResponse<T> {
        @Override
        public <U> BitvavoResponse<U> map(Function<T, U> mapper) {
            requireNonNull(mapper);
            return ok(mapper.apply(value));
        }

        @Override
        public T getOrThrow() {
            return value;
        }
    }

    record Error<T>(BitvavoErrorMessage errorMessage) implements BitvavoResponse<T> {
        @Override
        public <U> BitvavoResponse<U> map(Function<T, U> mapper) {
            return error(errorMessage);
        }

        @Override
        public T getOrThrow() {
            throw new NoSuchElementException(format("No value present, but there was an error: (%s)", errorMessage));
        }
    }
}
