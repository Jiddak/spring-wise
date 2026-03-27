package com.jid.springwise.balance.model.statement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jid.springwise.core.WiseApiMapper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WiseStatementTypeTest {

    private final ObjectMapper mapper = WiseApiMapper.getMapper();

    @Test
    void values_containsAllExpected() {
        WiseStatementType[] values = WiseStatementType.values();
        assertEquals(2, values.length);
    }

    @Test
    void compact_serialisesToCorrectString() throws Exception {
        String json = mapper.writeValueAsString(WiseStatementType.COMPACT);
        assertEquals("\"COMPACT\"", json);
    }

    @Test
    void flat_serialisesToCorrectString() throws Exception {
        String json = mapper.writeValueAsString(WiseStatementType.FLAT);
        assertEquals("\"FLAT\"", json);
    }

    @Test
    void compact_deserialisesFromString() throws Exception {
        WiseStatementType type = mapper.readValue("\"COMPACT\"", WiseStatementType.class);
        assertEquals(WiseStatementType.COMPACT, type);
    }

    @Test
    void flat_deserialisesFromString() throws Exception {
        WiseStatementType type = mapper.readValue("\"FLAT\"", WiseStatementType.class);
        assertEquals(WiseStatementType.FLAT, type);
    }

}
