package com.jid.springwise.balance.model.balance;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GetBalanceRequestTest {

    @Test
    void builder_setsAllFields() {
        GetBalanceRequest request = GetBalanceRequest.builder()
            .profileId(123L)
            .balanceId(456L)
            .build();

        assertEquals(123L, request.getProfileId());
        assertEquals(456L, request.getBalanceId());
    }

    @Test
    void noArgConstructor_fieldsAreZero() {
        GetBalanceRequest request = new GetBalanceRequest();
        assertEquals(0L, request.getProfileId());
        assertEquals(0L, request.getBalanceId());
    }

    @Test
    void setters_updateFields() {
        GetBalanceRequest request = new GetBalanceRequest();
        request.setProfileId(10L);
        request.setBalanceId(20L);

        assertEquals(10L, request.getProfileId());
        assertEquals(20L, request.getBalanceId());
    }

    @Test
    void equality_basedOnFields() {
        GetBalanceRequest a = GetBalanceRequest.builder().profileId(1L).balanceId(2L).build();
        GetBalanceRequest b = GetBalanceRequest.builder().profileId(1L).balanceId(2L).build();
        GetBalanceRequest c = GetBalanceRequest.builder().profileId(3L).balanceId(4L).build();

        assertEquals(a, b);
        assertNotEquals(a, c);
    }

}
