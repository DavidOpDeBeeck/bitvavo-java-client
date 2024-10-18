package be.davidopdebeeck.bitvavo.client.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

import static be.davidopdebeeck.bitvavo.client.utils.ThreadUtils.waitForMillis;
import static java.lang.Math.min;
import static java.lang.Math.pow;

public class RetryUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(RetryUtils.class);

    private static final int WAIT_TIME_EXPONENT = 3;
    private static final int WAIT_TIME_MULTIPLIER_MILLIS = 100;
    private static final int ONE_MINUTE_IN_MILLIS = 60 * 1000;

    public static void retryUntilTrue(Supplier<Boolean> supplier, String message) {
        retryUntilTrue(supplier, message, 1);
    }

    private static void retryUntilTrue(Supplier<Boolean> supplier, String message, int tries) {
        boolean result = supplier.get();
        if (!result) {
            int waitTimeInMillis = calculateWaitTimeInMillis(tries);
            LOGGER.warn("{} Trying again in {} milliseconds ...", message, waitTimeInMillis);
            waitForMillis(waitTimeInMillis);
            retryUntilTrue(supplier, message, ++tries);
        }
    }

    public static <T> T retry(ThrowingSupplier<T> supplier, String message) {
        return retry(supplier, message, 1);
    }

    private static <T> T retry(ThrowingSupplier<T> supplier, String message, int tries) {
        try {
            return supplier.get();
        } catch (Throwable exception) {
            int waitTimeInMillis = calculateWaitTimeInMillis(tries);
            LOGGER.warn("{} Trying again in {} milliseconds ...", message, waitTimeInMillis, exception);
            waitForMillis(waitTimeInMillis);
            return retry(supplier, message, ++tries);
        }
    }

    private static int calculateWaitTimeInMillis(int tries) {
        return (int) min(pow(tries, WAIT_TIME_EXPONENT) * WAIT_TIME_MULTIPLIER_MILLIS, ONE_MINUTE_IN_MILLIS);
    }
}
