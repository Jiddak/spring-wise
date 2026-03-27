package com.jid.springwise.balance.model.balance;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GetBalancesRequestTest {

    @Test
    void builder_setsAllFields() {
        GetBalancesRequest request = GetBalancesRequest.builder()
            .profileId(123L)
            .types("STANDARD")
            .build();

        assertEquals(123L, request.getProfileId());
        assertEquals("STANDARD", request.getTypes());
    }

    @Test
    void noArgConstructor_fieldsAreDefaulted() {
        GetBalancesRequest request = new GetBalancesRequest();
        assertEquals(0L, request.getProfileId());
        assertNull(request.getTypes());
    }

    @Test
    void setters_updateFields() {
        GetBalancesRequest request = new GetBalancesRequest();
        request.setProfileId(99L);
        request.setTypes("SAVINGS");

        assertEquals(99L, request.getProfileId());
        assertEquals("SAVINGS", request.getTypes());
    }

    @Test
    void equality_basedOnFields() {
        GetBalancesRequest a = GetBalancesRequest.builder().profileId(1L).types("STANDARD").build();
        GetBalancesRequest b = GetBalancesRequest.builder().profileId(1L).types("STANDARD").build();
        GetBalancesRequest c = GetBalancesRequest.builder().profileId(2L).types("SAVINGS").build();

        assertEquals(a, b);
        assertNotEquals(a, c);
    }

}
