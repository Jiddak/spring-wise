package com.jid.springwise.balance.model.statement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jid.springwise.core.WiseApiMapper;
import com.jid.springwise.core.model.WiseMoney;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class WiseStatementTransactionTest {

    private final ObjectMapper mapper = WiseApiMapper.getMapper();

    @Test
    void builder_setsAllFields() {
        WiseMoney money = new WiseMoney(new BigDecimal("50.00"), "GBP");
        ZonedDateTime date = ZonedDateTime.parse("2024-01-15T10:30:00Z");
        WiseStatementTransactionDetails details = WiseStatementTransactionDetails.builder()
            .description("Transfer").build();
        WiseStatementExchangeDetails exchange = WiseStatementExchangeDetails.builder()
            .rate(new BigDecimal("1.25")).build();

        WiseStatementTransaction tx = WiseStatementTransaction.builder()
            .type("DEBIT")
            .date(date)
            .amount(money)
            .totalFees(money)
            .details(details)
            .exchangeDetails(exchange)
            .runningBalance(money)
            .referenceNumber("REF-001")
            .build();

        assertEquals("DEBIT", tx.getType());
        assertEquals(date, tx.getDate());
        assertEquals(money, tx.getAmount());
        assertEquals(money, tx.getTotalFees());
        assertEquals(details, tx.getDetails());
        assertEquals(exchange, tx.getExchangeDetails());
        assertEquals(money, tx.getRunningBalance());
        assertEquals("REF-001", tx.getReferenceNumber());
    }

    @Test
    void noArgConstructor_allFieldsNull() {
        WiseStatementTransaction tx = new WiseStatementTransaction();
        assertNull(tx.getType());
        assertNull(tx.getDate());
        assertNull(tx.getAmount());
        assertNull(tx.getReferenceNumber());
    }

    @Test
    void equality_basedOnFields() {
        WiseStatementTransaction a = WiseStatementTransaction.builder().type("DEBIT").referenceNumber("REF-1").build();
        WiseStatementTransaction b = WiseStatementTransaction.builder().type("DEBIT").referenceNumber("REF-1").build();
        WiseStatementTransaction c = WiseStatementTransaction.builder().type("CREDIT").referenceNumber("REF-2").build();

        assertEquals(a, b);
        assertNotEquals(a, c);
    }

    @Test
    void serialisation_usesJsonPropertyNames() throws Exception {
        WiseStatementTransaction tx = WiseStatementTransaction.builder()
            .type("CREDIT")
            .referenceNumber("REF-123")
            .build();

        String json = mapper.writeValueAsString(tx);

        assertTrue(json.contains("\"type\":\"CREDIT\""));
        assertTrue(json.contains("\"referenceNumber\":\"REF-123\""));
    }

    @Test
    void serialisation_excludesNullFields() throws Exception {
        WiseStatementTransaction tx = WiseStatementTransaction.builder().type("DEBIT").build();
        String json = mapper.writeValueAsString(tx);
        assertFalse(json.contains("\"amount\""));
        assertFalse(json.contains("\"date\""));
    }

    @Test
    void deserialisation_mapsJsonPropertyNames() throws Exception {
        String json = "{\"type\":\"DEBIT\",\"date\":\"2024-01-15T10:30:00Z\",\"referenceNumber\":\"REF-456\"}";
        WiseStatementTransaction tx = mapper.readValue(json, WiseStatementTransaction.class);

        assertEquals("DEBIT", tx.getType());
        assertNotNull(tx.getDate());
        assertEquals("REF-456", tx.getReferenceNumber());
    }

}
