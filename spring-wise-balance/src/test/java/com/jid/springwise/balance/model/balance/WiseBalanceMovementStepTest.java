package com.jid.springwise.balance.model.balance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jid.springwise.core.WiseApiMapper;
import com.jid.springwise.core.model.WiseMoney;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WiseBalanceMovementStepTest {

    private final ObjectMapper mapper = WiseApiMapper.getMapper();

    @Test
    void builder_setsAllFields() {
        WiseMoney money = new WiseMoney(new BigDecimal("50.00"), "GBP");
        WiseBalanceMovementStep step = WiseBalanceMovementStep.builder()
            .id(1L)
            .type("CONVERSION")
            .creationTime("2024-01-01T10:00:00Z")
            .balancesAfter(List.of(money))
            .sourceAmount(money)
            .targetAmount(money)
            .fee(money)
            .rate(new BigDecimal("1.25"))
            .build();

        assertEquals(1L, step.getId());
        assertEquals("CONVERSION", step.getType());
        assertEquals("2024-01-01T10:00:00Z", step.getCreationTime());
        assertEquals(1, step.getBalancesAfter().size());
        assertEquals(money, step.getSourceAmount());
        assertEquals(money, step.getTargetAmount());
        assertEquals(money, step.getFee());
        assertEquals(new BigDecimal("1.25"), step.getRate());
    }

    @Test
    void noArgConstructor_allFieldsNull() {
        WiseBalanceMovementStep step = new WiseBalanceMovementStep();
        assertNull(step.getId());
        assertNull(step.getType());
        assertNull(step.getCreationTime());
        assertNull(step.getBalancesAfter());
        assertNull(step.getRate());
    }

    @Test
    void equality_basedOnFields() {
        WiseBalanceMovementStep a = WiseBalanceMovementStep.builder().id(1L).type("CONVERSION").build();
        WiseBalanceMovementStep b = WiseBalanceMovementStep.builder().id(1L).type("CONVERSION").build();
        WiseBalanceMovementStep c = WiseBalanceMovementStep.builder().id(2L).type("TRANSFER").build();

        assertEquals(a, b);
        assertNotEquals(a, c);
    }

    @Test
    void serialisation_usesJsonPropertyNames() throws Exception {
        WiseBalanceMovementStep step = WiseBalanceMovementStep.builder()
            .id(1L)
            .type("CONVERSION")
            .rate(new BigDecimal("1.5"))
            .build();

        String json = mapper.writeValueAsString(step);

        assertTrue(json.contains("\"id\":1"));
        assertTrue(json.contains("\"type\":\"CONVERSION\""));
        assertTrue(json.contains("\"rate\":1.5"));
    }

    @Test
    void serialisation_excludesNullFields() throws Exception {
        WiseBalanceMovementStep step = WiseBalanceMovementStep.builder().id(1L).build();
        String json = mapper.writeValueAsString(step);
        assertFalse(json.contains("\"type\""));
        assertFalse(json.contains("\"fee\""));
    }

    @Test
    void deserialisation_mapsJsonPropertyNames() throws Exception {
        String json = "{\"id\":3,\"type\":\"TRANSFER\",\"rate\":2.0}";
        WiseBalanceMovementStep step = mapper.readValue(json, WiseBalanceMovementStep.class);

        assertEquals(3L, step.getId());
        assertEquals("TRANSFER", step.getType());
        assertEquals(new BigDecimal("2.0"), step.getRate());
    }

}
