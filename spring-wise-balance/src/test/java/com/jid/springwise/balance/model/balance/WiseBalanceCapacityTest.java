package com.jid.springwise.balance.model.balance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jid.springwise.core.WiseApiMapper;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class WiseBalanceCapacityTest {

    private final ObjectMapper mapper = WiseApiMapper.getMapper();

    @Test
    void builder_setsAllFields() {
        WiseDepositLimit limit = WiseDepositLimit.builder()
            .amount(new BigDecimal("10000.00"))
            .currency("GBP")
            .build();
        WiseBalanceCapacity capacity = WiseBalanceCapacity.builder()
            .hasLimit(true)
            .depositLimit(limit)
            .build();

        assertTrue(capacity.getHasLimit());
        assertEquals(limit, capacity.getDepositLimit());
    }

    @Test
    void noArgConstructor_allFieldsNull() {
        WiseBalanceCapacity capacity = new WiseBalanceCapacity();
        assertNull(capacity.getHasLimit());
        assertNull(capacity.getDepositLimit());
    }

    @Test
    void equality_basedOnFields() {
        WiseBalanceCapacity a = WiseBalanceCapacity.builder().hasLimit(false).build();
        WiseBalanceCapacity b = WiseBalanceCapacity.builder().hasLimit(false).build();
        WiseBalanceCapacity c = WiseBalanceCapacity.builder().hasLimit(true).build();

        assertEquals(a, b);
        assertNotEquals(a, c);
    }

    @Test
    void serialisation_usesJsonPropertyNames() throws Exception {
        WiseBalanceCapacity capacity = WiseBalanceCapacity.builder().hasLimit(false).build();
        String json = mapper.writeValueAsString(capacity);
        assertTrue(json.contains("\"hasLimit\":false"));
    }

    @Test
    void serialisation_excludesNullFields() throws Exception {
        WiseBalanceCapacity capacity = WiseBalanceCapacity.builder().hasLimit(false).build();
        String json = mapper.writeValueAsString(capacity);
        assertFalse(json.contains("\"depositLimit\""));
    }

    @Test
    void deserialisation_mapsJsonPropertyNames() throws Exception {
        String json = "{\"hasLimit\":true,\"depositLimit\":{\"amount\":5000.00,\"currency\":\"GBP\"}}";
        WiseBalanceCapacity capacity = mapper.readValue(json, WiseBalanceCapacity.class);

        assertTrue(capacity.getHasLimit());
        assertNotNull(capacity.getDepositLimit());
        assertEquals("GBP", capacity.getDepositLimit().getCurrency());
    }

}
