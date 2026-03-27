package com.jid.springwise.balance.model.balance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jid.springwise.core.WiseApiMapper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WiseBalanceCreateTest {

    private final ObjectMapper mapper = WiseApiMapper.getMapper();

    @Test
    void builder_setsAllFields() {
        WiseBalanceCreate create = WiseBalanceCreate.builder()
            .type(WiseBalanceType.STANDARD)
            .currency("GBP")
            .name("My Balance")
            .build();

        assertEquals(WiseBalanceType.STANDARD, create.getType());
        assertEquals("GBP", create.getCurrency());
        assertEquals("My Balance", create.getName());
    }

    @Test
    void noArgConstructor_allFieldsNull() {
        WiseBalanceCreate create = new WiseBalanceCreate();
        assertNull(create.getType());
        assertNull(create.getCurrency());
        assertNull(create.getName());
    }

    @Test
    void equality_basedOnFields() {
        WiseBalanceCreate a = WiseBalanceCreate.builder().currency("GBP").type(WiseBalanceType.STANDARD).build();
        WiseBalanceCreate b = WiseBalanceCreate.builder().currency("GBP").type(WiseBalanceType.STANDARD).build();
        WiseBalanceCreate c = WiseBalanceCreate.builder().currency("USD").type(WiseBalanceType.SAVINGS).build();

        assertEquals(a, b);
        assertNotEquals(a, c);
    }

    @Test
    void serialisation_usesJsonPropertyNames() throws Exception {
        WiseBalanceCreate create = WiseBalanceCreate.builder()
            .type(WiseBalanceType.SAVINGS)
            .currency("EUR")
            .name("Savings")
            .build();

        String json = mapper.writeValueAsString(create);

        assertTrue(json.contains("\"type\":\"SAVINGS\""));
        assertTrue(json.contains("\"currency\":\"EUR\""));
        assertTrue(json.contains("\"name\":\"Savings\""));
    }

    @Test
    void serialisation_excludesNullFields() throws Exception {
        WiseBalanceCreate create = WiseBalanceCreate.builder().currency("GBP").build();
        String json = mapper.writeValueAsString(create);
        assertFalse(json.contains("\"type\""));
        assertFalse(json.contains("\"name\""));
    }

    @Test
    void deserialisation_mapsJsonPropertyNames() throws Exception {
        String json = "{\"type\":\"STANDARD\",\"currency\":\"GBP\",\"name\":\"Main\"}";
        WiseBalanceCreate create = mapper.readValue(json, WiseBalanceCreate.class);

        assertEquals(WiseBalanceType.STANDARD, create.getType());
        assertEquals("GBP", create.getCurrency());
        assertEquals("Main", create.getName());
    }

}
