package com.jid.springwise.balance.model.statement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jid.springwise.core.WiseApiMapper;
import com.jid.springwise.core.model.WiseMoney;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class WiseStatementExchangeDetailsTest {

    private final ObjectMapper mapper = WiseApiMapper.getMapper();

    @Test
    void builder_setsAllFields() {
        WiseMoney money = new WiseMoney(new BigDecimal("100.00"), "GBP");
        WiseStatementExchangeDetails details = WiseStatementExchangeDetails.builder()
            .forAmount(money)
            .rate(new BigDecimal("1.25"))
            .build();

        assertEquals(money, details.getForAmount());
        assertEquals(new BigDecimal("1.25"), details.getRate());
    }

    @Test
    void noArgConstructor_allFieldsNull() {
        WiseStatementExchangeDetails details = new WiseStatementExchangeDetails();
        assertNull(details.getForAmount());
        assertNull(details.getRate());
    }

    @Test
    void equality_basedOnFields() {
        WiseStatementExchangeDetails a = WiseStatementExchangeDetails.builder().rate(new BigDecimal("1.25")).build();
        WiseStatementExchangeDetails b = WiseStatementExchangeDetails.builder().rate(new BigDecimal("1.25")).build();
        WiseStatementExchangeDetails c = WiseStatementExchangeDetails.builder().rate(new BigDecimal("1.50")).build();

        assertEquals(a, b);
        assertNotEquals(a, c);
    }

    @Test
    void serialisation_usesJsonPropertyNames() throws Exception {
        WiseStatementExchangeDetails details = WiseStatementExchangeDetails.builder()
            .rate(new BigDecimal("1.25"))
            .build();

        String json = mapper.writeValueAsString(details);

        assertTrue(json.contains("\"rate\":1.25"));
    }

    @Test
    void serialisation_excludesNullFields() throws Exception {
        WiseStatementExchangeDetails details = WiseStatementExchangeDetails.builder().rate(new BigDecimal("1.0")).build();
        String json = mapper.writeValueAsString(details);
        assertFalse(json.contains("\"forAmount\""));
    }

    @Test
    void deserialisation_mapsJsonPropertyNames() throws Exception {
        String json = "{\"forAmount\":{\"value\":50.00,\"currency\":\"USD\"},\"rate\":0.8}";
        WiseStatementExchangeDetails details = mapper.readValue(json, WiseStatementExchangeDetails.class);

        assertNotNull(details.getForAmount());
        assertEquals("USD", details.getForAmount().getCurrency());
        assertEquals(new BigDecimal("0.8"), details.getRate());
    }

}
