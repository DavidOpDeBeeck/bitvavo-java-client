package be.davidopdebeeck.bitvavo.client.response;

import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang.exception.ExceptionUtils.getStackTrace;

public class BitvavoErrorMessage {

    private static final String RATE_LIMIT_ERROR_CODE = "105";
    private static final String CLIENT_EXCEPTION_ERROR_CODE = "CLIENT_EXCEPTION";

    public static BitvavoErrorMessage fromException(Throwable throwable) {
        return new BitvavoErrorMessage.Builder()
            .withErrorCode(CLIENT_EXCEPTION_ERROR_CODE)
            .withErrorMessage(getStackTrace(throwable))
            .build();
    }

    private final String errorCode;
    private final String errorMessage;

    private BitvavoErrorMessage(Builder builder) {
        errorCode = requireNonNull(builder.errorCode);
        errorMessage = requireNonNull(builder.errorMessage);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isRateLimitRelated() {
        return errorCode.equals(RATE_LIMIT_ERROR_CODE);
    }

    @Override
    public String toString() {
        return String.format("%s: %s", errorCode, errorMessage);
    }

    public static final class Builder {

        private String errorCode;
        private String errorMessage;

        public Builder withErrorCode(String errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public Builder withErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public BitvavoErrorMessage build() {
            return new BitvavoErrorMessage(this);
        }
    }
}
