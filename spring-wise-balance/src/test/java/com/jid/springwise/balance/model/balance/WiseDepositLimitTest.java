package com.jid.springwise.balance.model.balance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jid.springwise.core.WiseApiMapper;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class WiseDepositLimitTest {

    private final ObjectMapper mapper = WiseApiMapper.getMapper();

    @Test
    void builder_setsAllFields() {
        WiseDepositLimit limit = WiseDepositLimit.builder()
            .amount(new BigDecimal("10000.00"))
            .currency("GBP")
            .build();

        assertEquals(new BigDecimal("10000.00"), limit.getAmount());
        assertEquals("GBP", limit.getCurrency());
    }

    @Test
    void noArgConstructor_allFieldsNull() {
        WiseDepositLimit limit = new WiseDepositLimit();
        assertNull(limit.getAmount());
        assertNull(limit.getCurrency());
    }

    @Test
    void equality_basedOnFields() {
        WiseDepositLimit a = WiseDepositLimit.builder().amount(new BigDecimal("1000")).currency("GBP").build();
        WiseDepositLimit b = WiseDepositLimit.builder().amount(new BigDecimal("1000")).currency("GBP").build();
        WiseDepositLimit c = WiseDepositLimit.builder().amount(new BigDecimal("2000")).currency("USD").build();

        assertEquals(a, b);
        assertNotEquals(a, c);
    }

    @Test
    void serialisation_usesJsonPropertyNames() throws Exception {
        WiseDepositLimit limit = WiseDepositLimit.builder()
            .amount(new BigDecimal("5000.00"))
            .currency("EUR")
            .build();

        String json = mapper.writeValueAsString(limit);

        assertTrue(json.contains("\"amount\":5000.00"));
        assertTrue(json.contains("\"currency\":\"EUR\""));
    }

    @Test
    void serialisation_excludesNullFields() throws Exception {
        WiseDepositLimit limit = WiseDepositLimit.builder().currency("GBP").build();
        String json = mapper.writeValueAsString(limit);
        assertFalse(json.contains("\"amount\""));
    }

    @Test
    void deserialisation_mapsJsonPropertyNames() throws Exception {
        String json = "{\"amount\":2500.00,\"currency\":\"USD\"}";
        WiseDepositLimit limit = mapper.readValue(json, WiseDepositLimit.class);

        assertEquals(new BigDecimal("2500.00"), limit.getAmount());
        assertEquals("USD", limit.getCurrency());
    }

}
