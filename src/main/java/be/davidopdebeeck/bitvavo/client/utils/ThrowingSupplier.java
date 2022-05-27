package be.davidopdebeeck.bitvavo.client.utils;

public interface ThrowingSupplier<T> {
    T get() throws Throwable;
}
