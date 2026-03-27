package com.jid.springwise.balance.model.balance;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddExcessMoneyAccountRequestTest {

    @Test
    void builder_setsAllFields() {
        WiseExcessMoneyAccountCreate create = WiseExcessMoneyAccountCreate.builder().recipientId(456L).build();
        AddExcessMoneyAccountRequest request = AddExcessMoneyAccountRequest.builder()
            .profileId(123L)
            .excessMoneyAccount(create)
            .build();

        assertEquals(123L, request.getProfileId());
        assertEquals(create, request.getExcessMoneyAccount());
    }

    @Test
    void noArgConstructor_fieldsAreDefaulted() {
        AddExcessMoneyAccountRequest request = new AddExcessMoneyAccountRequest();
        assertEquals(0L, request.getProfileId());
        assertNull(request.getExcessMoneyAccount());
    }

    @Test
    void setters_updateFields() {
        AddExcessMoneyAccountRequest request = new AddExcessMoneyAccountRequest();
        request.setProfileId(88L);
        request.setExcessMoneyAccount(WiseExcessMoneyAccountCreate.builder().recipientId(99L).build());

        assertEquals(88L, request.getProfileId());
        assertEquals(99L, request.getExcessMoneyAccount().getRecipientId());
    }

    @Test
    void equality_basedOnFields() {
        WiseExcessMoneyAccountCreate create = WiseExcessMoneyAccountCreate.builder().recipientId(1L).build();
        AddExcessMoneyAccountRequest a = AddExcessMoneyAccountRequest.builder()
            .profileId(1L).excessMoneyAccount(create).build();
        AddExcessMoneyAccountRequest b = AddExcessMoneyAccountRequest.builder()
            .profileId(1L).excessMoneyAccount(create).build();
        AddExcessMoneyAccountRequest c = AddExcessMoneyAccountRequest.builder().profileId(2L).build();

        assertEquals(a, b);
        assertNotEquals(a, c);
    }

}
