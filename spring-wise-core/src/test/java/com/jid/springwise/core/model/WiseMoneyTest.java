package com.jid.springwise.core.model;

import com.jid.springwise.core.WiseApiMapper;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class WiseMoneyTest {

    @Test
    void builder_setsAllFields() {
        WiseMoney money = WiseMoney.builder()
            .value(BigDecimal.TEN)
            .currency("GBP")
            .build();

        assertEquals(BigDecimal.TEN, money.getValue());
        assertEquals("GBP", money.getCurrency());
    }

    @Test
    void noArgConstructor_allFieldsNull() {
        WiseMoney money = new WiseMoney();
        assertNull(money.getValue());
        assertNull(money.getCurrency());
    }

    @Test
    void allArgsConstructor_setsFields() {
        WiseMoney money = new WiseMoney(BigDecimal.ONE, "USD");
        assertEquals(BigDecimal.ONE, money.getValue());
        assertEquals("USD", money.getCurrency());
    }

    @Test
    void setters_updateFields() {
        WiseMoney money = new WiseMoney();
        money.setValue(new BigDecimal("99.99"));
        money.setCurrency("EUR");
        assertEquals(new BigDecimal("99.99"), money.getValue());
        assertEquals("EUR", money.getCurrency());
    }

    @Test
    void equality_basedOnFields() {
        WiseMoney a = WiseMoney.builder().value(BigDecimal.TEN).currency("GBP").build();
        WiseMoney b = WiseMoney.builder().value(BigDecimal.TEN).currency("GBP").build();
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void inequality_differentCurrency() {
        WiseMoney a = WiseMoney.builder().value(BigDecimal.TEN).currency("GBP").build();
        WiseMoney b = WiseMoney.builder().value(BigDecimal.TEN).currency("USD").build();
        assertNotEquals(a, b);
    }

    @Test
    void toString_containsCurrency() {
        WiseMoney money = WiseMoney.builder().value(BigDecimal.TEN).currency("GBP").build();
        assertTrue(money.toString().contains("GBP"));
    }

    @Test
    void serialisation_mapsJsonProperties() throws Exception {
        WiseMoney money = WiseMoney.builder().value(new BigDecimal("10.50")).currency("USD").build();
        String json = WiseApiMapper.sneakyWriteAsString(money);
        assertTrue(json.contains("\"value\":10.50"));
        assertTrue(json.contains("\"currency\":\"USD\""));
    }

    @Test
    void serialisation_excludesNullFields() throws Exception {
        WiseMoney money = WiseMoney.builder().currency("GBP").build();
        String json = WiseApiMapper.sneakyWriteAsString(money);
        assertFalse(json.contains("\"value\""));
        assertTrue(json.contains("\"currency\":\"GBP\""));
    }

    @Test
    void deserialisation_mapsJsonProperties() throws Exception {
        String json = "{\"value\":10.50,\"currency\":\"USD\"}";
        WiseMoney money = WiseApiMapper.getMapper().readValue(json, WiseMoney.class);
        assertEquals(new BigDecimal("10.50"), money.getValue());
        assertEquals("USD", money.getCurrency());
    }

}
