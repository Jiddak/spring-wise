package com.jid.springwise.balance.model.balance;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GetBalancesResponseTest {

    @Test
    void builder_setsField() {
        List<WiseBalance> balances = List.of(
            WiseBalance.builder().id(1L).currency("GBP").build(),
            WiseBalance.builder().id(2L).currency("USD").build()
        );
        GetBalancesResponse response = GetBalancesResponse.builder().balances(balances).build();

        assertEquals(2, response.getBalances().size());
    }

    @Test
    void noArgConstructor_fieldIsNull() {
        GetBalancesResponse response = new GetBalancesResponse();
        assertNull(response.getBalances());
    }

    @Test
    void setters_updateField() {
        GetBalancesResponse response = new GetBalancesResponse();
        List<WiseBalance> balances = List.of(WiseBalance.builder().id(1L).build());
        response.setBalances(balances);
        assertEquals(1, response.getBalances().size());
    }

    @Test
    void equality_basedOnFields() {
        List<WiseBalance> balances = List.of(WiseBalance.builder().id(1L).build());
        GetBalancesResponse a = GetBalancesResponse.builder().balances(balances).build();
        GetBalancesResponse b = GetBalancesResponse.builder().balances(balances).build();
        GetBalancesResponse c = GetBalancesResponse.builder().build();

        assertEquals(a, b);
        assertNotEquals(a, c);
    }

}
