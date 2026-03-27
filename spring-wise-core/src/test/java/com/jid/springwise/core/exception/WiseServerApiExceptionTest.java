package com.jid.springwise.core.exception;

import com.jid.springwise.core.model.WiseApiServerError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WiseServerApiExceptionTest {

    private WiseApiServerError serverError;

    @BeforeEach
    void setUp() {
        serverError = WiseApiServerError.builder()
            .error("Internal Server Error")
            .message("Something went wrong")
            .status(500)
            .build();
    }

    @Test
    void serverErrorConstructor_derivesMessageFromServerError() {
        WiseServerApiException ex = new WiseServerApiException(serverError);
        assertEquals("Something went wrong", ex.getMessage());
        assertSame(serverError, ex.getServerError());
        assertNull(ex.getCause());
    }

    @Test
    void messageConstructor_usesCustomMessage() {
        WiseServerApiException ex = new WiseServerApiException(serverError, "Custom message");
        assertEquals("Custom message", ex.getMessage());
        assertSame(serverError, ex.getServerError());
    }

    @Test
    void messageCauseConstructor_setsAll() {
        Throwable cause = new RuntimeException("cause");
        WiseServerApiException ex = new WiseServerApiException(serverError, "Custom message", cause);
        assertEquals("Custom message", ex.getMessage());
        assertSame(cause, ex.getCause());
        assertSame(serverError, ex.getServerError());
    }

    @Test
    void causeConstructor_derivesMessageFromServerErrorAndSetsCause() {
        Throwable cause = new RuntimeException("cause");
        WiseServerApiException ex = new WiseServerApiException(serverError, cause);
        assertEquals("Something went wrong", ex.getMessage());
        assertSame(cause, ex.getCause());
        assertSame(serverError, ex.getServerError());
    }

    @Test
    void serverErrorWithNullMessage_exceptionMessageIsNull() {
        WiseApiServerError noMessage = WiseApiServerError.builder().status(500).build();
        WiseServerApiException ex = new WiseServerApiException(noMessage);
        assertNull(ex.getMessage());
    }

    @Test
    void extendsWiseApiException() {
        assertThat(new WiseServerApiException(serverError)).isInstanceOf(WiseApiException.class);
    }

}
