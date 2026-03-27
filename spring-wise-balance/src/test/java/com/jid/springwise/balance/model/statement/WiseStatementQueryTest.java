package com.jid.springwise.balance.model.statement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jid.springwise.core.WiseApiMapper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WiseStatementQueryTest {

    private final ObjectMapper mapper = WiseApiMapper.getMapper();

    @Test
    void builder_setsAllFields() {
        WiseStatementQuery query = WiseStatementQuery.builder()
            .intervalStart("2024-01-01T00:00:00Z")
            .intervalEnd("2024-01-31T23:59:59Z")
            .currency("GBP")
            .accountId(42L)
            .build();

        assertEquals("2024-01-01T00:00:00Z", query.getIntervalStart());
        assertEquals("2024-01-31T23:59:59Z", query.getIntervalEnd());
        assertEquals("GBP", query.getCurrency());
        assertEquals(42L, query.getAccountId());
    }

    @Test
    void noArgConstructor_allFieldsNull() {
        WiseStatementQuery query = new WiseStatementQuery();
        assertNull(query.getIntervalStart());
        assertNull(query.getIntervalEnd());
        assertNull(query.getCurrency());
        assertNull(query.getAccountId());
    }

    @Test
    void equality_basedOnFields() {
        WiseStatementQuery a = WiseStatementQuery.builder().currency("GBP").accountId(1L).build();
        WiseStatementQuery b = WiseStatementQuery.builder().currency("GBP").accountId(1L).build();
        WiseStatementQuery c = WiseStatementQuery.builder().currency("USD").accountId(2L).build();

        assertEquals(a, b);
        assertNotEquals(a, c);
    }

    @Test
    void serialisation_usesJsonPropertyNames() throws Exception {
        WiseStatementQuery query = WiseStatementQuery.builder()
            .currency("GBP")
            .accountId(10L)
            .intervalStart("2024-01-01T00:00:00Z")
            .intervalEnd("2024-12-31T23:59:59Z")
            .build();

        String json = mapper.writeValueAsString(query);

        assertTrue(json.contains("\"currency\":\"GBP\""));
        assertTrue(json.contains("\"accountId\":10"));
        assertTrue(json.contains("\"intervalStart\""));
        assertTrue(json.contains("\"intervalEnd\""));
    }

    @Test
    void serialisation_excludesNullFields() throws Exception {
        WiseStatementQuery query = WiseStatementQuery.builder().currency("GBP").build();
        String json = mapper.writeValueAsString(query);
        assertFalse(json.contains("\"accountId\""));
        assertFalse(json.contains("\"intervalStart\""));
    }

    @Test
    void deserialisation_mapsJsonPropertyNames() throws Exception {
        String json = "{\"intervalStart\":\"2024-01-01T00:00:00Z\",\"intervalEnd\":\"2024-12-31T23:59:59Z\",\"currency\":\"EUR\",\"accountId\":5}";
        WiseStatementQuery query = mapper.readValue(json, WiseStatementQuery.class);

        assertEquals("2024-01-01T00:00:00Z", query.getIntervalStart());
        assertEquals("EUR", query.getCurrency());
        assertEquals(5L, query.getAccountId());
    }

}
