package com.jid.springwise.balance.model.balance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jid.springwise.core.WiseApiMapper;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class WiseBalanceAfterTest {

    private final ObjectMapper mapper = WiseApiMapper.getMapper();

    @Test
    void builder_setsAllFields() {
        WiseBalanceAfter after = WiseBalanceAfter.builder()
            .id(1L)
            .value(new BigDecimal("500.00"))
            .currency("GBP")
            .build();

        assertEquals(1L, after.getId());
        assertEquals(new BigDecimal("500.00"), after.getValue());
        assertEquals("GBP", after.getCurrency());
    }

    @Test
    void noArgConstructor_allFieldsNull() {
        WiseBalanceAfter after = new WiseBalanceAfter();
        assertNull(after.getId());
        assertNull(after.getValue());
        assertNull(after.getCurrency());
    }

    @Test
    void equality_basedOnFields() {
        WiseBalanceAfter a = WiseBalanceAfter.builder().id(1L).currency("GBP").build();
        WiseBalanceAfter b = WiseBalanceAfter.builder().id(1L).currency("GBP").build();
        WiseBalanceAfter c = WiseBalanceAfter.builder().id(2L).currency("USD").build();

        assertEquals(a, b);
        assertNotEquals(a, c);
    }

    @Test
    void serialisation_usesJsonPropertyNames() throws Exception {
        WiseBalanceAfter after = WiseBalanceAfter.builder()
            .id(1L)
            .value(new BigDecimal("100.00"))
            .currency("GBP")
            .build();

        String json = mapper.writeValueAsString(after);

        assertTrue(json.contains("\"id\":1"));
        assertTrue(json.contains("\"currency\":\"GBP\""));
    }

    @Test
    void serialisation_excludesNullFields() throws Exception {
        WiseBalanceAfter after = WiseBalanceAfter.builder().id(1L).build();
        String json = mapper.writeValueAsString(after);
        assertFalse(json.contains("\"currency\""));
        assertFalse(json.contains("\"value\""));
    }

    @Test
    void deserialisation_mapsJsonPropertyNames() throws Exception {
        String json = "{\"id\":7,\"value\":250.50,\"currency\":\"USD\"}";
        WiseBalanceAfter after = mapper.readValue(json, WiseBalanceAfter.class);

        assertEquals(7L, after.getId());
        assertEquals(new BigDecimal("250.50"), after.getValue());
        assertEquals("USD", after.getCurrency());
    }

}
