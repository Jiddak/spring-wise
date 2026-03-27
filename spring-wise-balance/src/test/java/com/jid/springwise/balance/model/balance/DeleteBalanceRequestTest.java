package com.jid.springwise.balance.model.balance;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteBalanceRequestTest {

    @Test
    void builder_setsAllFields() {
        DeleteBalanceRequest request = DeleteBalanceRequest.builder()
            .profileId(123L)
            .balanceId(456L)
            .build();

        assertEquals(123L, request.getProfileId());
        assertEquals(456L, request.getBalanceId());
    }

    @Test
    void noArgConstructor_fieldsAreZero() {
        DeleteBalanceRequest request = new DeleteBalanceRequest();
        assertEquals(0L, request.getProfileId());
        assertEquals(0L, request.getBalanceId());
    }

    @Test
    void setters_updateFields() {
        DeleteBalanceRequest request = new DeleteBalanceRequest();
        request.setProfileId(10L);
        request.setBalanceId(20L);

        assertEquals(10L, request.getProfileId());
        assertEquals(20L, request.getBalanceId());
    }

    @Test
    void equality_basedOnFields() {
        DeleteBalanceRequest a = DeleteBalanceRequest.builder().profileId(1L).balanceId(2L).build();
        DeleteBalanceRequest b = DeleteBalanceRequest.builder().profileId(1L).balanceId(2L).build();
        DeleteBalanceRequest c = DeleteBalanceRequest.builder().profileId(3L).balanceId(4L).build();

        assertEquals(a, b);
        assertNotEquals(a, c);
    }

}
