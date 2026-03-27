package com.jid.springwise.balance.model.balance;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddExcessMoneyAccountResponseTest {

    @Test
    void builder_setsField() {
        WiseExcessMoneyAccount account = WiseExcessMoneyAccount.builder()
            .userProfileId(123L).recipientId(456L).build();
        AddExcessMoneyAccountResponse response = AddExcessMoneyAccountResponse.builder()
            .excessMoneyAccount(account).build();
        assertEquals(account, response.getExcessMoneyAccount());
    }

    @Test
    void noArgConstructor_fieldIsNull() {
        AddExcessMoneyAccountResponse response = new AddExcessMoneyAccountResponse();
        assertNull(response.getExcessMoneyAccount());
    }

    @Test
    void setters_updateField() {
        AddExcessMoneyAccountResponse response = new AddExcessMoneyAccountResponse();
        WiseExcessMoneyAccount account = WiseExcessMoneyAccount.builder().recipientId(99L).build();
        response.setExcessMoneyAccount(account);
        assertEquals(99L, response.getExcessMoneyAccount().getRecipientId());
    }

    @Test
    void equality_basedOnFields() {
        WiseExcessMoneyAccount account = WiseExcessMoneyAccount.builder().userProfileId(1L).build();
        AddExcessMoneyAccountResponse a = AddExcessMoneyAccountResponse.builder().excessMoneyAccount(account).build();
        AddExcessMoneyAccountResponse b = AddExcessMoneyAccountResponse.builder().excessMoneyAccount(account).build();
        AddExcessMoneyAccountResponse c = AddExcessMoneyAccountResponse.builder().build();

        assertEquals(a, b);
        assertNotEquals(a, c);
    }

}
