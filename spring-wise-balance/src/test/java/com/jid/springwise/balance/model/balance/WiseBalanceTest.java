package com.jid.springwise.balance.model.balance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jid.springwise.core.WiseApiMapper;
import com.jid.springwise.core.model.WiseMoney;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class WiseBalanceTest {

    private final ObjectMapper mapper = WiseApiMapper.getMapper();

    @Test
    void builder_setsAllFields() {
        WiseMoney amount = new WiseMoney(new BigDecimal("100.00"), "GBP");
        WiseBalance balance = WiseBalance.builder()
            .id(1L)
            .currency("GBP")
            .type(WiseBalanceType.STANDARD)
            .name("My Balance")
            .icon(WiseBalanceIcon.builder().type("FLAG").value("GBP").build())
            .investmentState(WiseInvestmentState.NOT_INVESTED)
            .amount(amount)
            .reservedAmount(amount)
            .cashAmount(amount)
            .totalWorth(amount)
            .creationTime("2024-01-01T00:00:00Z")
            .modificationTime("2024-01-02T00:00:00Z")
            .visible(true)
            .build();

        assertEquals(1L, balance.getId());
        assertEquals("GBP", balance.getCurrency());
        assertEquals(WiseBalanceType.STANDARD, balance.getType());
        assertEquals("My Balance", balance.getName());
        assertNotNull(balance.getIcon());
        assertEquals(WiseInvestmentState.NOT_INVESTED, balance.getInvestmentState());
        assertEquals(amount, balance.getAmount());
        assertEquals(amount, balance.getReservedAmount());
        assertEquals(amount, balance.getCashAmount());
        assertEquals(amount, balance.getTotalWorth());
        assertEquals("2024-01-01T00:00:00Z", balance.getCreationTime());
        assertEquals("2024-01-02T00:00:00Z", balance.getModificationTime());
        assertTrue(balance.getVisible());
    }

    @Test
    void noArgConstructor_allFieldsNull() {
        WiseBalance balance = new WiseBalance();
        assertNull(balance.getId());
        assertNull(balance.getCurrency());
        assertNull(balance.getType());
        assertNull(balance.getName());
        assertNull(balance.getIcon());
        assertNull(balance.getInvestmentState());
        assertNull(balance.getAmount());
        assertNull(balance.getReservedAmount());
        assertNull(balance.getCashAmount());
        assertNull(balance.getTotalWorth());
        assertNull(balance.getCreationTime());
        assertNull(balance.getModificationTime());
        assertNull(balance.getVisible());
    }

    @Test
    void setters_updateFields() {
        WiseBalance balance = new WiseBalance();
        balance.setId(5L);
        balance.setCurrency("USD");
        balance.setVisible(false);

        assertEquals(5L, balance.getId());
        assertEquals("USD", balance.getCurrency());
        assertFalse(balance.getVisible());
    }

    @Test
    void equality_basedOnFields() {
        WiseMoney money = new WiseMoney(new BigDecimal("50.00"), "GBP");
        WiseBalance a = WiseBalance.builder().id(1L).currency("GBP").amount(money).build();
        WiseBalance b = WiseBalance.builder().id(1L).currency("GBP").amount(money).build();
        WiseBalance c = WiseBalance.builder().id(2L).currency("USD").build();

        assertEquals(a, b);
        assertNotEquals(a, c);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void serialisation_usesJsonPropertyNames() throws Exception {
        WiseBalance balance = WiseBalance.builder()
            .id(1L)
            .currency("GBP")
            .type(WiseBalanceType.STANDARD)
            .visible(true)
            .build();

        String json = mapper.writeValueAsString(balance);

        assertTrue(json.contains("\"id\":1"));
        assertTrue(json.contains("\"currency\":\"GBP\""));
        assertTrue(json.contains("\"type\":\"STANDARD\""));
        assertTrue(json.contains("\"visible\":true"));
    }

    @Test
    void serialisation_excludesNullFields() throws Exception {
        WiseBalance balance = WiseBalance.builder().id(1L).build();

        String json = mapper.writeValueAsString(balance);

        assertFalse(json.contains("\"currency\""));
        assertFalse(json.contains("\"name\""));
        assertFalse(json.contains("\"amount\""));
    }

    @Test
    void deserialisation_mapsJsonPropertyNames() throws Exception {
        String json = "{\"id\":42,\"currency\":\"EUR\",\"type\":\"SAVINGS\",\"visible\":false}";

        WiseBalance balance = mapper.readValue(json, WiseBalance.class);

        assertEquals(42L, balance.getId());
        assertEquals("EUR", balance.getCurrency());
        assertEquals(WiseBalanceType.SAVINGS, balance.getType());
        assertFalse(balance.getVisible());
    }

}
