package com.jid.springwise.balance.model.balance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jid.springwise.core.WiseApiMapper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WiseExcessMoneyAccountCreateTest {

    private final ObjectMapper mapper = WiseApiMapper.getMapper();

    @Test
    void builder_setsField() {
        WiseExcessMoneyAccountCreate create = WiseExcessMoneyAccountCreate.builder()
            .recipientId(456L)
            .build();

        assertEquals(456L, create.getRecipientId());
    }

    @Test
    void noArgConstructor_fieldIsNull() {
        WiseExcessMoneyAccountCreate create = new WiseExcessMoneyAccountCreate();
        assertNull(create.getRecipientId());
    }

    @Test
    void equality_basedOnFields() {
        WiseExcessMoneyAccountCreate a = WiseExcessMoneyAccountCreate.builder().recipientId(1L).build();
        WiseExcessMoneyAccountCreate b = WiseExcessMoneyAccountCreate.builder().recipientId(1L).build();
        WiseExcessMoneyAccountCreate c = WiseExcessMoneyAccountCreate.builder().recipientId(2L).build();

        assertEquals(a, b);
        assertNotEquals(a, c);
    }

    @Test
    void serialisation_usesJsonPropertyNames() throws Exception {
        WiseExcessMoneyAccountCreate create = WiseExcessMoneyAccountCreate.builder().recipientId(99L).build();
        String json = mapper.writeValueAsString(create);
        assertTrue(json.contains("\"recipientId\":99"));
    }

    @Test
    void serialisation_excludesNullFields() throws Exception {
        WiseExcessMoneyAccountCreate create = new WiseExcessMoneyAccountCreate();
        String json = mapper.writeValueAsString(create);
        assertEquals("{}", json);
    }

    @Test
    void deserialisation_mapsJsonPropertyNames() throws Exception {
        String json = "{\"recipientId\":77}";
        WiseExcessMoneyAccountCreate create = mapper.readValue(json, WiseExcessMoneyAccountCreate.class);
        assertEquals(77L, create.getRecipientId());
    }

}
