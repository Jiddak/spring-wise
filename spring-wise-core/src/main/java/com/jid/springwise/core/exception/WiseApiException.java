package com.jid.springwise.core.exception;

public class WiseApiException extends RuntimeException {

    public WiseApiException() {
        super();
    }

    public WiseApiException(String message) {
        super(message);
    }

    public WiseApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public WiseApiException(Throwable cause) {
        super(cause);
    }

}
