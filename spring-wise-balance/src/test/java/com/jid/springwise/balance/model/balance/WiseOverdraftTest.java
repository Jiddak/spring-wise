package com.jid.springwise.balance.model.balance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jid.springwise.core.WiseApiMapper;
import com.jid.springwise.core.model.WiseMoney;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WiseOverdraftTest {

    private final ObjectMapper mapper = WiseApiMapper.getMapper();

    @Test
    void builder_setsAllFields() {
        WiseMoney money = new WiseMoney(new BigDecimal("500.00"), "GBP");
        WiseOverdraft overdraft = WiseOverdraft.builder()
            .limit(money)
            .used(money)
            .available(money)
            .availableByCurrency(List.of(money))
            .build();

        assertEquals(money, overdraft.getLimit());
        assertEquals(money, overdraft.getUsed());
        assertEquals(money, overdraft.getAvailable());
        assertEquals(1, overdraft.getAvailableByCurrency().size());
    }

    @Test
    void noArgConstructor_allFieldsNull() {
        WiseOverdraft overdraft = new WiseOverdraft();
        assertNull(overdraft.getLimit());
        assertNull(overdraft.getUsed());
        assertNull(overdraft.getAvailable());
        assertNull(overdraft.getAvailableByCurrency());
    }

    @Test
    void equality_basedOnFields() {
        WiseMoney money = new WiseMoney(new BigDecimal("100"), "GBP");
        WiseOverdraft a = WiseOverdraft.builder().limit(money).build();
        WiseOverdraft b = WiseOverdraft.builder().limit(money).build();
        WiseOverdraft c = WiseOverdraft.builder().build();

        assertEquals(a, b);
        assertNotEquals(a, c);
    }

    @Test
    void serialisation_usesJsonPropertyNames() throws Exception {
        WiseMoney money = new WiseMoney(new BigDecimal("100.00"), "GBP");
        WiseOverdraft overdraft = WiseOverdraft.builder().limit(money).build();

        String json = mapper.writeValueAsString(overdraft);

        assertTrue(json.contains("\"limit\""));
    }

    @Test
    void serialisation_excludesNullFields() throws Exception {
        WiseOverdraft overdraft = WiseOverdraft.builder().build();
        String json = mapper.writeValueAsString(overdraft);
        assertEquals("{}", json);
    }

    @Test
    void deserialisation_mapsJsonPropertyNames() throws Exception {
        String json = "{\"limit\":{\"value\":500.00,\"currency\":\"GBP\"}}";
        WiseOverdraft overdraft = mapper.readValue(json, WiseOverdraft.class);

        assertNotNull(overdraft.getLimit());
        assertEquals("GBP", overdraft.getLimit().getCurrency());
    }

}
