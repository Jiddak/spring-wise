package com.jid.springwise.balance.model.balance;

import com.jid.springwise.core.model.WiseMoney;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class GetTotalFundsResponseTest {

    @Test
    void builder_setsField() {
        WiseTotalFunds totalFunds = WiseTotalFunds.builder()
            .totalWorth(new WiseMoney(new BigDecimal("1000.00"), "GBP"))
            .build();
        GetTotalFundsResponse response = GetTotalFundsResponse.builder().totalFunds(totalFunds).build();
        assertEquals(totalFunds, response.getTotalFunds());
    }

    @Test
    void noArgConstructor_fieldIsNull() {
        GetTotalFundsResponse response = new GetTotalFundsResponse();
        assertNull(response.getTotalFunds());
    }

    @Test
    void setters_updateField() {
        GetTotalFundsResponse response = new GetTotalFundsResponse();
        WiseTotalFunds totalFunds = WiseTotalFunds.builder().build();
        response.setTotalFunds(totalFunds);
        assertNotNull(response.getTotalFunds());
    }

    @Test
    void equality_basedOnFields() {
        WiseTotalFunds totalFunds = WiseTotalFunds.builder().build();
        GetTotalFundsResponse a = GetTotalFundsResponse.builder().totalFunds(totalFunds).build();
        GetTotalFundsResponse b = GetTotalFundsResponse.builder().totalFunds(totalFunds).build();
        GetTotalFundsResponse c = GetTotalFundsResponse.builder().build();

        assertEquals(a, b);
        assertNotEquals(a, c);
    }

}
