package com.jid.springwise.core.exception;

import com.jid.springwise.core.model.WiseApiServerError;

public class WiseServerApiException extends WiseApiException {

    private final WiseApiServerError serverError;

    public WiseServerApiException(WiseApiServerError serverError) {
        super(serverError.getMessage());
        this.serverError = serverError;
    }

    public WiseServerApiException(WiseApiServerError serverError, String message) {
        super(message);
        this.serverError = serverError;
    }

    public WiseServerApiException(WiseApiServerError serverError, String message, Throwable cause) {
        super(message, cause);
        this.serverError = serverError;
    }

    public WiseServerApiException(WiseApiServerError serverError, Throwable cause) {
        super(serverError.getMessage(), cause);
        this.serverError = serverError;
    }

    public WiseApiServerError getServerError() {
        return serverError;
    }

}
