package com.jid.springwise.balance.model.balance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jid.springwise.core.WiseApiMapper;
import com.jid.springwise.core.model.WiseMoney;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WiseBalanceMovementResponseTest {

    private final ObjectMapper mapper = WiseApiMapper.getMapper();

    @Test
    void builder_setsAllFields() {
        WiseMoney money = new WiseMoney(new BigDecimal("100.00"), "GBP");
        WiseBalanceAfter balanceAfter = WiseBalanceAfter.builder().id(1L).currency("GBP").build();
        WiseBalanceMovementResponse response = WiseBalanceMovementResponse.builder()
            .id(99L)
            .type("CONVERSION")
            .state("COMPLETED")
            .balancesAfter(List.of(balanceAfter))
            .creationTime("2024-01-01T10:00:00Z")
            .sourceAmount(money)
            .targetAmount(money)
            .rate(new BigDecimal("1.2500"))
            .feeAmounts(List.of(money))
            .build();

        assertEquals(99L, response.getId());
        assertEquals("CONVERSION", response.getType());
        assertEquals("COMPLETED", response.getState());
        assertEquals(1, response.getBalancesAfter().size());
        assertEquals("2024-01-01T10:00:00Z", response.getCreationTime());
        assertEquals(money, response.getSourceAmount());
        assertEquals(money, response.getTargetAmount());
        assertEquals(new BigDecimal("1.2500"), response.getRate());
        assertEquals(1, response.getFeeAmounts().size());
    }

    @Test
    void noArgConstructor_allFieldsNull() {
        WiseBalanceMovementResponse response = new WiseBalanceMovementResponse();
        assertNull(response.getId());
        assertNull(response.getType());
        assertNull(response.getState());
        assertNull(response.getBalancesAfter());
        assertNull(response.getRate());
    }

    @Test
    void equality_basedOnFields() {
        WiseBalanceMovementResponse a = WiseBalanceMovementResponse.builder().id(1L).state("COMPLETED").build();
        WiseBalanceMovementResponse b = WiseBalanceMovementResponse.builder().id(1L).state("COMPLETED").build();
        WiseBalanceMovementResponse c = WiseBalanceMovementResponse.builder().id(2L).state("PENDING").build();

        assertEquals(a, b);
        assertNotEquals(a, c);
    }

    @Test
    void serialisation_usesJsonPropertyNames() throws Exception {
        WiseBalanceMovementResponse response = WiseBalanceMovementResponse.builder()
            .id(1L)
            .state("COMPLETED")
            .rate(new BigDecimal("1.25"))
            .build();

        String json = mapper.writeValueAsString(response);

        assertTrue(json.contains("\"id\":1"));
        assertTrue(json.contains("\"state\":\"COMPLETED\""));
        assertTrue(json.contains("\"rate\":1.25"));
    }

    @Test
    void serialisation_excludesNullFields() throws Exception {
        WiseBalanceMovementResponse response = WiseBalanceMovementResponse.builder().id(1L).build();
        String json = mapper.writeValueAsString(response);
        assertFalse(json.contains("\"type\""));
        assertFalse(json.contains("\"state\""));
        assertFalse(json.contains("\"balancesAfter\""));
    }

    @Test
    void deserialisation_mapsJsonPropertyNames() throws Exception {
        String json = "{\"id\":5,\"type\":\"CONVERSION\",\"state\":\"COMPLETED\",\"rate\":1.5}";
        WiseBalanceMovementResponse response = mapper.readValue(json, WiseBalanceMovementResponse.class);

        assertEquals(5L, response.getId());
        assertEquals("CONVERSION", response.getType());
        assertEquals("COMPLETED", response.getState());
        assertEquals(new BigDecimal("1.5"), response.getRate());
    }

}
