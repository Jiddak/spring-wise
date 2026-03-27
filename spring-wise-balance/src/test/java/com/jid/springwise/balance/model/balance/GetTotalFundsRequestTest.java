package com.jid.springwise.balance.model.balance;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GetTotalFundsRequestTest {

    @Test
    void builder_setsAllFields() {
        GetTotalFundsRequest request = GetTotalFundsRequest.builder()
            .profileId(123L)
            .currency("GBP")
            .build();

        assertEquals(123L, request.getProfileId());
        assertEquals("GBP", request.getCurrency());
    }

    @Test
    void noArgConstructor_fieldsAreDefaulted() {
        GetTotalFundsRequest request = new GetTotalFundsRequest();
        assertEquals(0L, request.getProfileId());
        assertNull(request.getCurrency());
    }

    @Test
    void setters_updateFields() {
        GetTotalFundsRequest request = new GetTotalFundsRequest();
        request.setProfileId(42L);
        request.setCurrency("USD");

        assertEquals(42L, request.getProfileId());
        assertEquals("USD", request.getCurrency());
    }

    @Test
    void equality_basedOnFields() {
        GetTotalFundsRequest a = GetTotalFundsRequest.builder().profileId(1L).currency("GBP").build();
        GetTotalFundsRequest b = GetTotalFundsRequest.builder().profileId(1L).currency("GBP").build();
        GetTotalFundsRequest c = GetTotalFundsRequest.builder().profileId(2L).currency("EUR").build();

        assertEquals(a, b);
        assertNotEquals(a, c);
    }

}
