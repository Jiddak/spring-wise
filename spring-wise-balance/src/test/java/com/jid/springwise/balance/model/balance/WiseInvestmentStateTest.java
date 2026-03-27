package com.jid.springwise.balance.model.balance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jid.springwise.core.WiseApiMapper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WiseInvestmentStateTest {

    private final ObjectMapper mapper = WiseApiMapper.getMapper();

    @Test
    void values_containsAllExpected() {
        WiseInvestmentState[] values = WiseInvestmentState.values();
        assertEquals(5, values.length);
    }

    @Test
    void notInvested_serialisesToCorrectString() throws Exception {
        String json = mapper.writeValueAsString(WiseInvestmentState.NOT_INVESTED);
        assertEquals("\"NOT_INVESTED\"", json);
    }

    @Test
    void invested_serialisesToCorrectString() throws Exception {
        String json = mapper.writeValueAsString(WiseInvestmentState.INVESTED);
        assertEquals("\"INVESTED\"", json);
    }

    @Test
    void investing_serialisesToCorrectString() throws Exception {
        String json = mapper.writeValueAsString(WiseInvestmentState.INVESTING);
        assertEquals("\"INVESTING\"", json);
    }

    @Test
    void divesting_serialisesToCorrectString() throws Exception {
        String json = mapper.writeValueAsString(WiseInvestmentState.DIVESTING);
        assertEquals("\"DIVESTING\"", json);
    }

    @Test
    void unknown_serialisesToCorrectString() throws Exception {
        String json = mapper.writeValueAsString(WiseInvestmentState.UNKNOWN);
        assertEquals("\"UNKNOWN\"", json);
    }

    @Test
    void deserialisesFromString() throws Exception {
        assertEquals(WiseInvestmentState.INVESTED, mapper.readValue("\"INVESTED\"", WiseInvestmentState.class));
        assertEquals(WiseInvestmentState.DIVESTING, mapper.readValue("\"DIVESTING\"", WiseInvestmentState.class));
        assertEquals(WiseInvestmentState.UNKNOWN, mapper.readValue("\"UNKNOWN\"", WiseInvestmentState.class));
    }

}
