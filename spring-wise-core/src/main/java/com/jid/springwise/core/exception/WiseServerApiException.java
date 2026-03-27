package com.jid.springwise.core.exception;

import com.jid.springwise.core.model.WiseApiServerError;

public class WiseServerApiException extends WiseApiException {

    private final WiseApiServerError serverError;

    public WiseServerApiException(WiseApiServerError serverError) {
        this.serverError = serverError;
    }

    public WiseApiServerError getServerError() {
        return serverError;
    }

}
