package com.jid.springwise.balance.model.statement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jid.springwise.core.WiseApiMapper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WiseStatementMerchantTest {

    private final ObjectMapper mapper = WiseApiMapper.getMapper();

    @Test
    void builder_setsAllFields() {
        WiseStatementMerchant merchant = WiseStatementMerchant.builder()
            .name("Tesco")
            .firstLine("1 High Street")
            .postCode("EC1A 1BB")
            .city("London")
            .state("ENG")
            .country("GB")
            .category("SUPERMARKET")
            .build();

        assertEquals("Tesco", merchant.getName());
        assertEquals("1 High Street", merchant.getFirstLine());
        assertEquals("EC1A 1BB", merchant.getPostCode());
        assertEquals("London", merchant.getCity());
        assertEquals("ENG", merchant.getState());
        assertEquals("GB", merchant.getCountry());
        assertEquals("SUPERMARKET", merchant.getCategory());
    }

    @Test
    void noArgConstructor_allFieldsNull() {
        WiseStatementMerchant merchant = new WiseStatementMerchant();
        assertNull(merchant.getName());
        assertNull(merchant.getFirstLine());
        assertNull(merchant.getCategory());
    }

    @Test
    void equality_basedOnFields() {
        WiseStatementMerchant a = WiseStatementMerchant.builder().name("Tesco").city("London").build();
        WiseStatementMerchant b = WiseStatementMerchant.builder().name("Tesco").city("London").build();
        WiseStatementMerchant c = WiseStatementMerchant.builder().name("Sainsbury").build();

        assertEquals(a, b);
        assertNotEquals(a, c);
    }

    @Test
    void serialisation_usesJsonPropertyNames() throws Exception {
        WiseStatementMerchant merchant = WiseStatementMerchant.builder()
            .name("Tesco")
            .category("GROCERY")
            .build();

        String json = mapper.writeValueAsString(merchant);

        assertTrue(json.contains("\"name\":\"Tesco\""));
        assertTrue(json.contains("\"category\":\"GROCERY\""));
    }

    @Test
    void serialisation_excludesNullFields() throws Exception {
        WiseStatementMerchant merchant = WiseStatementMerchant.builder().name("Tesco").build();
        String json = mapper.writeValueAsString(merchant);
        assertFalse(json.contains("\"firstLine\""));
        assertFalse(json.contains("\"postCode\""));
    }

    @Test
    void deserialisation_mapsJsonPropertyNames() throws Exception {
        String json = "{\"name\":\"Amazon\",\"country\":\"GB\",\"category\":\"RETAIL\"}";
        WiseStatementMerchant merchant = mapper.readValue(json, WiseStatementMerchant.class);

        assertEquals("Amazon", merchant.getName());
        assertEquals("GB", merchant.getCountry());
        assertEquals("RETAIL", merchant.getCategory());
    }

}
