package com.jid.springwise.core.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WiseApiExceptionTest {

    @Test
    void noArgConstructor_hasNoMessageOrCause() {
        WiseApiException ex = new WiseApiException();
        assertNull(ex.getMessage());
        assertNull(ex.getCause());
    }

    @Test
    void messageConstructor_setsMessage() {
        WiseApiException ex = new WiseApiException("test message");
        assertEquals("test message", ex.getMessage());
        assertNull(ex.getCause());
    }

    @Test
    void messageCauseConstructor_setsBoth() {
        Throwable cause = new RuntimeException("root cause");
        WiseApiException ex = new WiseApiException("test message", cause);
        assertEquals("test message", ex.getMessage());
        assertSame(cause, ex.getCause());
    }

    @Test
    void causeConstructor_setsCause() {
        Throwable cause = new RuntimeException("root cause");
        WiseApiException ex = new WiseApiException(cause);
        assertSame(cause, ex.getCause());
    }

    @Test
    void isRuntimeException() {
        assertThat(new WiseApiException()).isInstanceOf(RuntimeException.class);
    }

}
