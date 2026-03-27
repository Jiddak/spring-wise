package com.jid.springwise.balance.model.statement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jid.springwise.core.WiseApiMapper;
import com.jid.springwise.core.model.WiseMoney;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class WiseStatementTransactionDetailsTest {

    private final ObjectMapper mapper = WiseApiMapper.getMapper();

    @Test
    void builder_setsAllFields() {
        WiseMoney money = new WiseMoney(new BigDecimal("100.00"), "GBP");
        WiseStatementMerchant merchant = WiseStatementMerchant.builder().name("Tesco").build();

        WiseStatementTransactionDetails details = WiseStatementTransactionDetails.builder()
            .type("CARD")
            .description("Supermarket")
            .amount(money)
            .senderName("John Doe")
            .senderAccount("GB29NWBK60161331926819")
            .paymentReference("PAY-001")
            .category("GROCERY")
            .merchant(merchant)
            .sourceAmount(money)
            .targetAmount(money)
            .fee(money)
            .rate(new BigDecimal("1.25"))
            .build();

        assertEquals("CARD", details.getType());
        assertEquals("Supermarket", details.getDescription());
        assertEquals(money, details.getAmount());
        assertEquals("John Doe", details.getSenderName());
        assertEquals("GB29NWBK60161331926819", details.getSenderAccount());
        assertEquals("PAY-001", details.getPaymentReference());
        assertEquals("GROCERY", details.getCategory());
        assertEquals(merchant, details.getMerchant());
        assertEquals(money, details.getSourceAmount());
        assertEquals(money, details.getTargetAmount());
        assertEquals(money, details.getFee());
        assertEquals(new BigDecimal("1.25"), details.getRate());
    }

    @Test
    void noArgConstructor_allFieldsNull() {
        WiseStatementTransactionDetails details = new WiseStatementTransactionDetails();
        assertNull(details.getType());
        assertNull(details.getDescription());
        assertNull(details.getMerchant());
        assertNull(details.getRate());
    }

    @Test
    void equality_basedOnFields() {
        WiseStatementTransactionDetails a = WiseStatementTransactionDetails.builder()
            .type("CARD").description("Coffee").build();
        WiseStatementTransactionDetails b = WiseStatementTransactionDetails.builder()
            .type("CARD").description("Coffee").build();
        WiseStatementTransactionDetails c = WiseStatementTransactionDetails.builder()
            .type("TRANSFER").description("Rent").build();

        assertEquals(a, b);
        assertNotEquals(a, c);
    }

    @Test
    void serialisation_usesJsonPropertyNames() throws Exception {
        WiseStatementTransactionDetails details = WiseStatementTransactionDetails.builder()
            .type("CARD")
            .description("Coffee")
            .senderName("Jane")
            .build();

        String json = mapper.writeValueAsString(details);

        assertTrue(json.contains("\"type\":\"CARD\""));
        assertTrue(json.contains("\"description\":\"Coffee\""));
        assertTrue(json.contains("\"senderName\":\"Jane\""));
    }

    @Test
    void serialisation_excludesNullFields() throws Exception {
        WiseStatementTransactionDetails details = WiseStatementTransactionDetails.builder().type("CARD").build();
        String json = mapper.writeValueAsString(details);
        assertFalse(json.contains("\"merchant\""));
        assertFalse(json.contains("\"rate\""));
    }

    @Test
    void deserialisation_mapsJsonPropertyNames() throws Exception {
        String json = "{\"type\":\"TRANSFER\",\"description\":\"Salary\",\"senderName\":\"Employer\",\"rate\":1.0}";
        WiseStatementTransactionDetails details = mapper.readValue(json, WiseStatementTransactionDetails.class);

        assertEquals("TRANSFER", details.getType());
        assertEquals("Salary", details.getDescription());
        assertEquals("Employer", details.getSenderName());
        assertEquals(new BigDecimal("1.0"), details.getRate());
    }

}
