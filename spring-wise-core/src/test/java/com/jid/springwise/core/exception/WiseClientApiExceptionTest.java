package com.jid.springwise.core.exception;

import com.jid.springwise.core.model.WiseApiClientError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WiseClientApiExceptionTest {

    private WiseApiClientError error1;
    private WiseApiClientError error2;
    private List<WiseApiClientError> errors;

    @BeforeEach
    void setUp() {
        error1 = WiseApiClientError.builder().code("400").message("Bad request").build();
        error2 = WiseApiClientError.builder().code("422").message("Unprocessable").build();
        errors = List.of(error1, error2);
    }

    @Test
    void errorsConstructor_buildsCommaDelimitedMessage() {
        WiseClientApiException ex = new WiseClientApiException(errors);
        assertEquals(error1 + ", " + error2, ex.getMessage());
        assertSame(errors, ex.getErrors());
        assertNull(ex.getCause());
    }

    @Test
    void messageConstructor_usesCustomMessage() {
        WiseClientApiException ex = new WiseClientApiException(errors, "Custom message");
        assertEquals("Custom message", ex.getMessage());
        assertSame(errors, ex.getErrors());
    }

    @Test
    void messageCauseConstructor_setsAll() {
        Throwable cause = new RuntimeException("cause");
        WiseClientApiException ex = new WiseClientApiException(errors, "Custom message", cause);
        assertEquals("Custom message", ex.getMessage());
        assertSame(cause, ex.getCause());
        assertSame(errors, ex.getErrors());
    }

    @Test
    void causeConstructor_buildsMessageAndSetsCause() {
        Throwable cause = new RuntimeException("cause");
        WiseClientApiException ex = new WiseClientApiException(errors, cause);
        assertEquals(error1 + ", " + error2, ex.getMessage());
        assertSame(cause, ex.getCause());
        assertSame(errors, ex.getErrors());
    }

    @Test
    void singleError_messageIsSingleToString() {
        WiseClientApiException ex = new WiseClientApiException(List.of(error1));
        assertEquals(error1.toString(), ex.getMessage());
    }

    @Test
    void emptyErrors_producesEmptyMessage() {
        WiseClientApiException ex = new WiseClientApiException(List.of());
        assertEquals("", ex.getMessage());
    }

    @Test
    void extendsWiseApiException() {
        assertThat(new WiseClientApiException(errors)).isInstanceOf(WiseApiException.class);
    }

}
