package com.jid.springwise.balance.model.balance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jid.springwise.core.WiseApiMapper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WiseExcessMoneyAccountTest {

    private final ObjectMapper mapper = WiseApiMapper.getMapper();

    @Test
    void builder_setsAllFields() {
        WiseExcessMoneyAccount account = WiseExcessMoneyAccount.builder()
            .userProfileId(123L)
            .recipientId(456L)
            .build();

        assertEquals(123L, account.getUserProfileId());
        assertEquals(456L, account.getRecipientId());
    }

    @Test
    void noArgConstructor_allFieldsNull() {
        WiseExcessMoneyAccount account = new WiseExcessMoneyAccount();
        assertNull(account.getUserProfileId());
        assertNull(account.getRecipientId());
    }

    @Test
    void equality_basedOnFields() {
        WiseExcessMoneyAccount a = WiseExcessMoneyAccount.builder().userProfileId(1L).recipientId(2L).build();
        WiseExcessMoneyAccount b = WiseExcessMoneyAccount.builder().userProfileId(1L).recipientId(2L).build();
        WiseExcessMoneyAccount c = WiseExcessMoneyAccount.builder().userProfileId(3L).recipientId(4L).build();

        assertEquals(a, b);
        assertNotEquals(a, c);
    }

    @Test
    void serialisation_usesJsonPropertyNames() throws Exception {
        WiseExcessMoneyAccount account = WiseExcessMoneyAccount.builder()
            .userProfileId(123L)
            .recipientId(456L)
            .build();

        String json = mapper.writeValueAsString(account);

        assertTrue(json.contains("\"userProfileId\":123"));
        assertTrue(json.contains("\"recipientId\":456"));
    }

    @Test
    void serialisation_excludesNullFields() throws Exception {
        WiseExcessMoneyAccount account = WiseExcessMoneyAccount.builder().userProfileId(123L).build();
        String json = mapper.writeValueAsString(account);
        assertFalse(json.contains("\"recipientId\""));
    }

    @Test
    void deserialisation_mapsJsonPropertyNames() throws Exception {
        String json = "{\"userProfileId\":10,\"recipientId\":20}";
        WiseExcessMoneyAccount account = mapper.readValue(json, WiseExcessMoneyAccount.class);

        assertEquals(10L, account.getUserProfileId());
        assertEquals(20L, account.getRecipientId());
    }

}
