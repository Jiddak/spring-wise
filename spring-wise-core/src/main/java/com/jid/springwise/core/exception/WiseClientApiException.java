package com.jid.springwise.core.exception;

import com.jid.springwise.core.model.WiseApiClientError;

import java.util.List;

public class WiseClientApiException extends WiseApiException {

    private final List<WiseApiClientError> errors;

    public WiseClientApiException(List<WiseApiClientError> errors) {
        this.errors = errors;
    }

    public List<WiseApiClientError> getErrors() {
        return errors;
    }

}
