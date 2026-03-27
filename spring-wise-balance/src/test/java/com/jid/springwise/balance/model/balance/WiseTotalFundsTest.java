package com.jid.springwise.balance.model.balance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jid.springwise.core.WiseApiMapper;
import com.jid.springwise.core.model.WiseMoney;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class WiseTotalFundsTest {

    private final ObjectMapper mapper = WiseApiMapper.getMapper();

    @Test
    void builder_setsAllFields() {
        WiseMoney money = new WiseMoney(new BigDecimal("1000.00"), "GBP");
        WiseTotalFunds totalFunds = WiseTotalFunds.builder()
            .totalWorth(money)
            .totalAvailable(money)
            .totalCash(money)
            .overdraft(WiseOverdraft.builder().build())
            .build();

        assertEquals(money, totalFunds.getTotalWorth());
        assertEquals(money, totalFunds.getTotalAvailable());
        assertEquals(money, totalFunds.getTotalCash());
        assertNotNull(totalFunds.getOverdraft());
    }

    @Test
    void noArgConstructor_allFieldsNull() {
        WiseTotalFunds totalFunds = new WiseTotalFunds();
        assertNull(totalFunds.getTotalWorth());
        assertNull(totalFunds.getTotalAvailable());
        assertNull(totalFunds.getTotalCash());
        assertNull(totalFunds.getOverdraft());
    }

    @Test
    void equality_basedOnFields() {
        WiseMoney money = new WiseMoney(new BigDecimal("100"), "GBP");
        WiseTotalFunds a = WiseTotalFunds.builder().totalWorth(money).build();
        WiseTotalFunds b = WiseTotalFunds.builder().totalWorth(money).build();
        WiseTotalFunds c = WiseTotalFunds.builder().build();

        assertEquals(a, b);
        assertNotEquals(a, c);
    }

    @Test
    void serialisation_usesJsonPropertyNames() throws Exception {
        WiseMoney money = new WiseMoney(new BigDecimal("500.00"), "GBP");
        WiseTotalFunds totalFunds = WiseTotalFunds.builder()
            .totalWorth(money)
            .totalAvailable(money)
            .build();

        String json = mapper.writeValueAsString(totalFunds);

        assertTrue(json.contains("\"totalWorth\""));
        assertTrue(json.contains("\"totalAvailable\""));
    }

    @Test
    void serialisation_excludesNullFields() throws Exception {
        WiseTotalFunds totalFunds = WiseTotalFunds.builder().build();
        String json = mapper.writeValueAsString(totalFunds);
        assertEquals("{}", json);
    }

    @Test
    void deserialisation_mapsJsonPropertyNames() throws Exception {
        String json = "{\"totalWorth\":{\"value\":1000.00,\"currency\":\"GBP\"},\"totalAvailable\":{\"value\":800.00,\"currency\":\"GBP\"}}";
        WiseTotalFunds totalFunds = mapper.readValue(json, WiseTotalFunds.class);

        assertNotNull(totalFunds.getTotalWorth());
        assertEquals("GBP", totalFunds.getTotalWorth().getCurrency());
        assertNotNull(totalFunds.getTotalAvailable());
        assertNull(totalFunds.getTotalCash());
    }

}
