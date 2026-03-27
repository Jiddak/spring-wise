package com.jid.springwise.balance.model.balance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jid.springwise.core.WiseApiMapper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WiseBalanceIconTest {

    private final ObjectMapper mapper = WiseApiMapper.getMapper();

    @Test
    void builder_setsAllFields() {
        WiseBalanceIcon icon = WiseBalanceIcon.builder()
            .type("FLAG")
            .value("GBP")
            .build();

        assertEquals("FLAG", icon.getType());
        assertEquals("GBP", icon.getValue());
    }

    @Test
    void noArgConstructor_allFieldsNull() {
        WiseBalanceIcon icon = new WiseBalanceIcon();
        assertNull(icon.getType());
        assertNull(icon.getValue());
    }

    @Test
    void equality_basedOnFields() {
        WiseBalanceIcon a = WiseBalanceIcon.builder().type("FLAG").value("GBP").build();
        WiseBalanceIcon b = WiseBalanceIcon.builder().type("FLAG").value("GBP").build();
        WiseBalanceIcon c = WiseBalanceIcon.builder().type("EMOJI").value("💷").build();

        assertEquals(a, b);
        assertNotEquals(a, c);
    }

    @Test
    void serialisation_usesJsonPropertyNames() throws Exception {
        WiseBalanceIcon icon = WiseBalanceIcon.builder().type("FLAG").value("USD").build();
        String json = mapper.writeValueAsString(icon);
        assertTrue(json.contains("\"type\":\"FLAG\""));
        assertTrue(json.contains("\"value\":\"USD\""));
    }

    @Test
    void serialisation_excludesNullFields() throws Exception {
        WiseBalanceIcon icon = WiseBalanceIcon.builder().type("FLAG").build();
        String json = mapper.writeValueAsString(icon);
        assertFalse(json.contains("\"value\""));
    }

    @Test
    void deserialisation_mapsJsonPropertyNames() throws Exception {
        String json = "{\"type\":\"FLAG\",\"value\":\"EUR\"}";
        WiseBalanceIcon icon = mapper.readValue(json, WiseBalanceIcon.class);

        assertEquals("FLAG", icon.getType());
        assertEquals("EUR", icon.getValue());
    }

}
