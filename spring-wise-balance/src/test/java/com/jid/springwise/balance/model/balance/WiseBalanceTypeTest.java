package com.jid.springwise.balance.model.balance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jid.springwise.core.WiseApiMapper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WiseBalanceTypeTest {

    private final ObjectMapper mapper = WiseApiMapper.getMapper();

    @Test
    void values_containsAllExpected() {
        WiseBalanceType[] values = WiseBalanceType.values();
        assertEquals(2, values.length);
    }

    @Test
    void standard_serialisesToCorrectString() throws Exception {
        String json = mapper.writeValueAsString(WiseBalanceType.STANDARD);
        assertEquals("\"STANDARD\"", json);
    }

    @Test
    void savings_serialisesToCorrectString() throws Exception {
        String json = mapper.writeValueAsString(WiseBalanceType.SAVINGS);
        assertEquals("\"SAVINGS\"", json);
    }

    @Test
    void standard_deserialisesFromString() throws Exception {
        WiseBalanceType type = mapper.readValue("\"STANDARD\"", WiseBalanceType.class);
        assertEquals(WiseBalanceType.STANDARD, type);
    }

    @Test
    void savings_deserialisesFromString() throws Exception {
        WiseBalanceType type = mapper.readValue("\"SAVINGS\"", WiseBalanceType.class);
        assertEquals(WiseBalanceType.SAVINGS, type);
    }

}
