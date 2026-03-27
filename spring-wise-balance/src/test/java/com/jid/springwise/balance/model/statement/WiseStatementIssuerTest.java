package com.jid.springwise.balance.model.statement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jid.springwise.core.WiseApiMapper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WiseStatementIssuerTest {

    private final ObjectMapper mapper = WiseApiMapper.getMapper();

    @Test
    void builder_setsAllFields() {
        WiseStatementIssuer issuer = WiseStatementIssuer.builder()
            .name("Wise")
            .firstLine("1 Streetham Place")
            .city("London")
            .postCode("EC2M 7EB")
            .stateCode("ENG")
            .country("United Kingdom")
            .build();

        assertEquals("Wise", issuer.getName());
        assertEquals("1 Streetham Place", issuer.getFirstLine());
        assertEquals("London", issuer.getCity());
        assertEquals("EC2M 7EB", issuer.getPostCode());
        assertEquals("ENG", issuer.getStateCode());
        assertEquals("United Kingdom", issuer.getCountry());
    }

    @Test
    void noArgConstructor_allFieldsNull() {
        WiseStatementIssuer issuer = new WiseStatementIssuer();
        assertNull(issuer.getName());
        assertNull(issuer.getFirstLine());
        assertNull(issuer.getCity());
        assertNull(issuer.getPostCode());
        assertNull(issuer.getStateCode());
        assertNull(issuer.getCountry());
    }

    @Test
    void equality_basedOnFields() {
        WiseStatementIssuer a = WiseStatementIssuer.builder().name("Wise").city("London").build();
        WiseStatementIssuer b = WiseStatementIssuer.builder().name("Wise").city("London").build();
        WiseStatementIssuer c = WiseStatementIssuer.builder().name("Other").city("Paris").build();

        assertEquals(a, b);
        assertNotEquals(a, c);
    }

    @Test
    void serialisation_usesJsonPropertyNames() throws Exception {
        WiseStatementIssuer issuer = WiseStatementIssuer.builder()
            .name("Wise")
            .city("London")
            .country("GB")
            .build();

        String json = mapper.writeValueAsString(issuer);

        assertTrue(json.contains("\"name\":\"Wise\""));
        assertTrue(json.contains("\"city\":\"London\""));
        assertTrue(json.contains("\"country\":\"GB\""));
    }

    @Test
    void serialisation_excludesNullFields() throws Exception {
        WiseStatementIssuer issuer = WiseStatementIssuer.builder().name("Wise").build();
        String json = mapper.writeValueAsString(issuer);
        assertFalse(json.contains("\"firstLine\""));
        assertFalse(json.contains("\"postCode\""));
    }

    @Test
    void deserialisation_mapsJsonPropertyNames() throws Exception {
        String json = "{\"name\":\"Wise\",\"firstLine\":\"1 Test\",\"city\":\"London\",\"country\":\"GB\"}";
        WiseStatementIssuer issuer = mapper.readValue(json, WiseStatementIssuer.class);

        assertEquals("Wise", issuer.getName());
        assertEquals("1 Test", issuer.getFirstLine());
        assertEquals("London", issuer.getCity());
        assertEquals("GB", issuer.getCountry());
    }

}
