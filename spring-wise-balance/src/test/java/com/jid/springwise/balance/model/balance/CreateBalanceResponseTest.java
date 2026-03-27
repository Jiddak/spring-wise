package com.jid.springwise.balance.model.balance;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateBalanceResponseTest {

    @Test
    void builder_setsField() {
        WiseBalance balance = WiseBalance.builder().id(1L).currency("GBP").build();
        CreateBalanceResponse response = CreateBalanceResponse.builder().balance(balance).build();
        assertEquals(balance, response.getBalance());
    }

    @Test
    void noArgConstructor_fieldIsNull() {
        CreateBalanceResponse response = new CreateBalanceResponse();
        assertNull(response.getBalance());
    }

    @Test
    void setters_updateField() {
        CreateBalanceResponse response = new CreateBalanceResponse();
        WiseBalance balance = WiseBalance.builder().id(2L).build();
        response.setBalance(balance);
        assertEquals(balance, response.getBalance());
    }

    @Test
    void equality_basedOnFields() {
        WiseBalance balance = WiseBalance.builder().id(1L).build();
        CreateBalanceResponse a = CreateBalanceResponse.builder().balance(balance).build();
        CreateBalanceResponse b = CreateBalanceResponse.builder().balance(balance).build();
        CreateBalanceResponse c = CreateBalanceResponse.builder().build();

        assertEquals(a, b);
        assertNotEquals(a, c);
    }

}
