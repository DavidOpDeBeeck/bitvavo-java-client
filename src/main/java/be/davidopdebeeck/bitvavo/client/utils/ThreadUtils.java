package be.davidopdebeeck.bitvavo.client.utils;

import static java.lang.Thread.sleep;

public class ThreadUtils {

    public static void waitForMillis(int millis) {
        try {
            sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
