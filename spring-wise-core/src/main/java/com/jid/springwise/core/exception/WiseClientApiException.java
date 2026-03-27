package com.jid.springwise.core.exception;

import com.jid.springwise.core.model.WiseApiClientError;

import java.util.List;
import java.util.stream.Collectors;

public class WiseClientApiException extends WiseApiException {

    private final List<WiseApiClientError> errors;

    public WiseClientApiException(List<WiseApiClientError> errors) {
        super(buildMessage(errors));
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
        super(buildMessage(errors), cause);
        this.errors = errors;
    }

    public List<WiseApiClientError> getErrors() {
        return errors;
    }

    private static String buildMessage(List<WiseApiClientError> errors) {
        return errors.stream()
            .map(WiseApiClientError::toString)
            .collect(Collectors.joining(", "));
    }

}
