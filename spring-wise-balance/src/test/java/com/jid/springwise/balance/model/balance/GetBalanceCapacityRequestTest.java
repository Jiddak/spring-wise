package com.jid.springwise.balance.model.balance;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GetBalanceCapacityRequestTest {

    @Test
    void builder_setsAllFields() {
        GetBalanceCapacityRequest request = GetBalanceCapacityRequest.builder()
            .profileId(123L)
            .currency("GBP")
            .build();

        assertEquals(123L, request.getProfileId());
        assertEquals("GBP", request.getCurrency());
    }

    @Test
    void noArgConstructor_fieldsAreDefaulted() {
        GetBalanceCapacityRequest request = new GetBalanceCapacityRequest();
        assertEquals(0L, request.getProfileId());
        assertNull(request.getCurrency());
    }

    @Test
    void setters_updateFields() {
        GetBalanceCapacityRequest request = new GetBalanceCapacityRequest();
        request.setProfileId(55L);
        request.setCurrency("EUR");

        assertEquals(55L, request.getProfileId());
        assertEquals("EUR", request.getCurrency());
    }

    @Test
    void equality_basedOnFields() {
        GetBalanceCapacityRequest a = GetBalanceCapacityRequest.builder().profileId(1L).currency("GBP").build();
        GetBalanceCapacityRequest b = GetBalanceCapacityRequest.builder().profileId(1L).currency("GBP").build();
        GetBalanceCapacityRequest c = GetBalanceCapacityRequest.builder().profileId(2L).currency("USD").build();

        assertEquals(a, b);
        assertNotEquals(a, c);
    }

}
