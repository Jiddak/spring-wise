package com.jid.springwise.balance.model.statement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jid.springwise.core.WiseApiMapper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WiseStatementAddressTest {

    private final ObjectMapper mapper = WiseApiMapper.getMapper();

    @Test
    void builder_setsAllFields() {
        WiseStatementAddress address = WiseStatementAddress.builder()
            .addressFirstLine("123 Main St")
            .city("London")
            .postCode("SW1A 1AA")
            .stateCode("ENG")
            .countryName("United Kingdom")
            .build();

        assertEquals("123 Main St", address.getAddressFirstLine());
        assertEquals("London", address.getCity());
        assertEquals("SW1A 1AA", address.getPostCode());
        assertEquals("ENG", address.getStateCode());
        assertEquals("United Kingdom", address.getCountryName());
    }

    @Test
    void noArgConstructor_allFieldsNull() {
        WiseStatementAddress address = new WiseStatementAddress();
        assertNull(address.getAddressFirstLine());
        assertNull(address.getCity());
        assertNull(address.getPostCode());
        assertNull(address.getStateCode());
        assertNull(address.getCountryName());
    }

    @Test
    void equality_basedOnFields() {
        WiseStatementAddress a = WiseStatementAddress.builder().city("London").postCode("SW1A").build();
        WiseStatementAddress b = WiseStatementAddress.builder().city("London").postCode("SW1A").build();
        WiseStatementAddress c = WiseStatementAddress.builder().city("Paris").build();

        assertEquals(a, b);
        assertNotEquals(a, c);
    }

    @Test
    void serialisation_usesJsonPropertyNames() throws Exception {
        WiseStatementAddress address = WiseStatementAddress.builder()
            .addressFirstLine("1 Test St")
            .city("London")
            .build();

        String json = mapper.writeValueAsString(address);

        assertTrue(json.contains("\"addressFirstLine\":\"1 Test St\""));
        assertTrue(json.contains("\"city\":\"London\""));
    }

    @Test
    void serialisation_excludesNullFields() throws Exception {
        WiseStatementAddress address = WiseStatementAddress.builder().city("London").build();
        String json = mapper.writeValueAsString(address);
        assertFalse(json.contains("\"postCode\""));
        assertFalse(json.contains("\"stateCode\""));
    }

    @Test
    void deserialisation_mapsJsonPropertyNames() throws Exception {
        String json = "{\"addressFirstLine\":\"5 High St\",\"city\":\"Manchester\",\"postCode\":\"M1 1AA\"}";
        WiseStatementAddress address = mapper.readValue(json, WiseStatementAddress.class);

        assertEquals("5 High St", address.getAddressFirstLine());
        assertEquals("Manchester", address.getCity());
        assertEquals("M1 1AA", address.getPostCode());
    }

}
