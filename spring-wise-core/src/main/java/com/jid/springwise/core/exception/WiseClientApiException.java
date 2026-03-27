package com.jid.springwise.core.exception;

import com.jid.springwise.core.model.WiseApiClientError;

import java.util.List;

public class WiseClientApiException extends WiseApiException {

    private final List<WiseApiClientError> errors;

    public WiseClientApiException(List<WiseApiClientError> errors) {
        this.errors = errors;
    }

    public WiseClientApiException(List<WiseApiClientError> errors, String message) {
        super(message);
        this.errors = errors;
    }

    public WiseClientApiException(List<WiseApiClientError> errors, String message, Throwable cause) {
        super(message, cause);
        this.errors = errors;
    }

    public WiseClientApiException(List<WiseApiClientError> errors, Throwable cause) {
        super(cause);
        this.errors = errors;
    }

    public List<WiseApiClientError> getErrors() {
        return errors;
    }

}
