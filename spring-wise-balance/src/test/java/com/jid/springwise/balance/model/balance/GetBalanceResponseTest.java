package com.jid.springwise.balance.model.balance;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GetBalanceResponseTest {

    @Test
    void builder_setsField() {
        WiseBalance balance = WiseBalance.builder().id(1L).currency("GBP").build();
        GetBalanceResponse response = GetBalanceResponse.builder().balance(balance).build();
        assertEquals(balance, response.getBalance());
    }

    @Test
    void noArgConstructor_fieldIsNull() {
        GetBalanceResponse response = new GetBalanceResponse();
        assertNull(response.getBalance());
    }

    @Test
    void setters_updateField() {
        GetBalanceResponse response = new GetBalanceResponse();
        WiseBalance balance = WiseBalance.builder().id(5L).build();
        response.setBalance(balance);
        assertEquals(balance, response.getBalance());
    }

    @Test
    void equality_basedOnFields() {
        WiseBalance balance = WiseBalance.builder().id(1L).build();
        GetBalanceResponse a = GetBalanceResponse.builder().balance(balance).build();
        GetBalanceResponse b = GetBalanceResponse.builder().balance(balance).build();
        GetBalanceResponse c = GetBalanceResponse.builder().build();

        assertEquals(a, b);
        assertNotEquals(a, c);
    }

}
