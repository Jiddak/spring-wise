package com.jid.springwise.balance.model.statement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jid.springwise.core.WiseApiMapper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WiseStatementAccountHolderTest {

    private final ObjectMapper mapper = WiseApiMapper.getMapper();

    @Test
    void builder_setsAllFields() {
        WiseStatementAddress address = WiseStatementAddress.builder().city("London").build();
        WiseStatementAccountHolder holder = WiseStatementAccountHolder.builder()
            .type("PERSONAL")
            .address(address)
            .firstName("John")
            .lastName("Doe")
            .build();

        assertEquals("PERSONAL", holder.getType());
        assertEquals(address, holder.getAddress());
        assertEquals("John", holder.getFirstName());
        assertEquals("Doe", holder.getLastName());
    }

    @Test
    void noArgConstructor_allFieldsNull() {
        WiseStatementAccountHolder holder = new WiseStatementAccountHolder();
        assertNull(holder.getType());
        assertNull(holder.getAddress());
        assertNull(holder.getFirstName());
        assertNull(holder.getLastName());
    }

    @Test
    void equality_basedOnFields() {
        WiseStatementAccountHolder a = WiseStatementAccountHolder.builder().firstName("John").lastName("Doe").build();
        WiseStatementAccountHolder b = WiseStatementAccountHolder.builder().firstName("John").lastName("Doe").build();
        WiseStatementAccountHolder c = WiseStatementAccountHolder.builder().firstName("Jane").build();

        assertEquals(a, b);
        assertNotEquals(a, c);
    }

    @Test
    void serialisation_usesJsonPropertyNames() throws Exception {
        WiseStatementAccountHolder holder = WiseStatementAccountHolder.builder()
            .type("PERSONAL")
            .firstName("John")
            .lastName("Doe")
            .build();

        String json = mapper.writeValueAsString(holder);

        assertTrue(json.contains("\"type\":\"PERSONAL\""));
        assertTrue(json.contains("\"firstName\":\"John\""));
        assertTrue(json.contains("\"lastName\":\"Doe\""));
    }

    @Test
    void serialisation_excludesNullFields() throws Exception {
        WiseStatementAccountHolder holder = WiseStatementAccountHolder.builder().firstName("John").build();
        String json = mapper.writeValueAsString(holder);
        assertFalse(json.contains("\"type\""));
        assertFalse(json.contains("\"address\""));
        assertFalse(json.contains("\"lastName\""));
    }

    @Test
    void deserialisation_mapsJsonPropertyNames() throws Exception {
        String json = "{\"type\":\"BUSINESS\",\"firstName\":\"Jane\",\"lastName\":\"Smith\"}";
        WiseStatementAccountHolder holder = mapper.readValue(json, WiseStatementAccountHolder.class);

        assertEquals("BUSINESS", holder.getType());
        assertEquals("Jane", holder.getFirstName());
        assertEquals("Smith", holder.getLastName());
    }

}
