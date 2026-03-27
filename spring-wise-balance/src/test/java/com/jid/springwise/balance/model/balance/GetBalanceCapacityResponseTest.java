package com.jid.springwise.balance.model.balance;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GetBalanceCapacityResponseTest {

    @Test
    void builder_setsField() {
        WiseBalanceCapacity capacity = WiseBalanceCapacity.builder().hasLimit(false).build();
        GetBalanceCapacityResponse response = GetBalanceCapacityResponse.builder().capacity(capacity).build();
        assertEquals(capacity, response.getCapacity());
    }

    @Test
    void noArgConstructor_fieldIsNull() {
        GetBalanceCapacityResponse response = new GetBalanceCapacityResponse();
        assertNull(response.getCapacity());
    }

    @Test
    void setters_updateField() {
        GetBalanceCapacityResponse response = new GetBalanceCapacityResponse();
        WiseBalanceCapacity capacity = WiseBalanceCapacity.builder().hasLimit(true).build();
        response.setCapacity(capacity);
        assertTrue(response.getCapacity().getHasLimit());
    }

    @Test
    void equality_basedOnFields() {
        WiseBalanceCapacity capacity = WiseBalanceCapacity.builder().hasLimit(false).build();
        GetBalanceCapacityResponse a = GetBalanceCapacityResponse.builder().capacity(capacity).build();
        GetBalanceCapacityResponse b = GetBalanceCapacityResponse.builder().capacity(capacity).build();
        GetBalanceCapacityResponse c = GetBalanceCapacityResponse.builder().build();

        assertEquals(a, b);
        assertNotEquals(a, c);
    }

}
