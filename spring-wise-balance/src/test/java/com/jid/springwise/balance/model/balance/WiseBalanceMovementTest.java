package com.jid.springwise.balance.model.balance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jid.springwise.core.WiseApiMapper;
import com.jid.springwise.core.model.WiseMoney;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class WiseBalanceMovementTest {

    private final ObjectMapper mapper = WiseApiMapper.getMapper();

    @Test
    void builder_setsAllFields() {
        WiseMoney amount = new WiseMoney(new BigDecimal("100.00"), "GBP");
        WiseBalanceMovement movement = WiseBalanceMovement.builder()
            .quoteId("quote-123")
            .sourceBalanceId(1L)
            .targetBalanceId(2L)
            .amount(amount)
            .build();

        assertEquals("quote-123", movement.getQuoteId());
        assertEquals(1L, movement.getSourceBalanceId());
        assertEquals(2L, movement.getTargetBalanceId());
        assertEquals(amount, movement.getAmount());
    }

    @Test
    void noArgConstructor_allFieldsNull() {
        WiseBalanceMovement movement = new WiseBalanceMovement();
        assertNull(movement.getQuoteId());
        assertNull(movement.getSourceBalanceId());
        assertNull(movement.getTargetBalanceId());
        assertNull(movement.getAmount());
    }

    @Test
    void equality_basedOnFields() {
        WiseBalanceMovement a = WiseBalanceMovement.builder().sourceBalanceId(1L).targetBalanceId(2L).build();
        WiseBalanceMovement b = WiseBalanceMovement.builder().sourceBalanceId(1L).targetBalanceId(2L).build();
        WiseBalanceMovement c = WiseBalanceMovement.builder().sourceBalanceId(3L).targetBalanceId(4L).build();

        assertEquals(a, b);
        assertNotEquals(a, c);
    }

    @Test
    void serialisation_usesJsonPropertyNames() throws Exception {
        WiseBalanceMovement movement = WiseBalanceMovement.builder()
            .quoteId("q-1")
            .sourceBalanceId(10L)
            .targetBalanceId(20L)
            .build();

        String json = mapper.writeValueAsString(movement);

        assertTrue(json.contains("\"quoteId\":\"q-1\""));
        assertTrue(json.contains("\"sourceBalanceId\":10"));
        assertTrue(json.contains("\"targetBalanceId\":20"));
    }

    @Test
    void serialisation_excludesNullFields() throws Exception {
        WiseBalanceMovement movement = WiseBalanceMovement.builder().sourceBalanceId(1L).build();
        String json = mapper.writeValueAsString(movement);
        assertFalse(json.contains("\"quoteId\""));
        assertFalse(json.contains("\"amount\""));
    }

    @Test
    void deserialisation_mapsJsonPropertyNames() throws Exception {
        String json = "{\"quoteId\":\"q-abc\",\"sourceBalanceId\":5,\"targetBalanceId\":6}";
        WiseBalanceMovement movement = mapper.readValue(json, WiseBalanceMovement.class);

        assertEquals("q-abc", movement.getQuoteId());
        assertEquals(5L, movement.getSourceBalanceId());
        assertEquals(6L, movement.getTargetBalanceId());
    }

}
