package com.jid.springwise.balance.model.balance;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteBalanceResponseTest {

    @Test
    void builder_setsField() {
        DeleteBalanceResponse response = DeleteBalanceResponse.builder().success(true).build();
        assertTrue(response.isSuccess());
    }

    @Test
    void noArgConstructor_successIsFalse() {
        DeleteBalanceResponse response = new DeleteBalanceResponse();
        assertFalse(response.isSuccess());
    }

    @Test
    void setters_updateField() {
        DeleteBalanceResponse response = new DeleteBalanceResponse();
        response.setSuccess(true);
        assertTrue(response.isSuccess());
    }

    @Test
    void equality_basedOnFields() {
        DeleteBalanceResponse a = DeleteBalanceResponse.builder().success(true).build();
        DeleteBalanceResponse b = DeleteBalanceResponse.builder().success(true).build();
        DeleteBalanceResponse c = DeleteBalanceResponse.builder().success(false).build();

        assertEquals(a, b);
        assertNotEquals(a, c);
    }

}
