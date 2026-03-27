package com.jid.springwise.core;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jid.springwise.core.model.WiseApiServerError;
import com.jid.springwise.core.model.WiseMoney;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WiseApiMapperTest {

    @Test
    void getMapper_returnsNonNull() {
        assertNotNull(WiseApiMapper.getMapper());
    }

    @Test
    void getMapper_returnsSameInstanceEachTime() {
        assertSame(WiseApiMapper.getMapper(), WiseApiMapper.getMapper());
    }

    @Test
    void getObjectReader_returnsNonNull() {
        assertNotNull(WiseApiMapper.getObjectReader());
    }

    @Test
    void getObjectWriter_returnsNonNull() {
        assertNotNull(WiseApiMapper.getObjectWriter());
    }

    @Test
    void sneakyWriteAsString_serialisesObjectToJson() {
        WiseMoney money = WiseMoney.builder().value(BigDecimal.TEN).currency("GBP").build();
        String json = WiseApiMapper.sneakyWriteAsString(money);
        assertThat(json).contains("\"value\":10");
        assertThat(json).contains("\"currency\":\"GBP\"");
    }

    @Test
    void sneakyWriteAsString_excludesNullFields() {
        WiseMoney money = WiseMoney.builder().currency("GBP").build();
        String json = WiseApiMapper.sneakyWriteAsString(money);
        assertThat(json).doesNotContain("value");
        assertThat(json).contains("\"currency\":\"GBP\"");
    }

    @Test
    void sneakyReadValue_byClass_deserialisesJson() {
        String json = "{\"value\":10.50,\"currency\":\"USD\"}";
        WiseMoney money = WiseApiMapper.sneakyReadValue(json, WiseMoney.class);
        assertEquals(new BigDecimal("10.50"), money.getValue());
        assertEquals("USD", money.getCurrency());
    }

    @Test
    void sneakyReadValue_byTypeReference_deserialisesJsonList() {
        String json = "[{\"value\":10,\"currency\":\"GBP\"},{\"value\":5,\"currency\":\"USD\"}]";
        List<WiseMoney> monies = WiseApiMapper.sneakyReadValue(json, new TypeReference<>() {});
        assertEquals(2, monies.size());
        assertEquals("GBP", monies.get(0).getCurrency());
        assertEquals("USD", monies.get(1).getCurrency());
    }

    @Test
    void sneakyReadValue_ignoresUnknownProperties() {
        String json = "{\"value\":10,\"currency\":\"GBP\",\"unknownField\":\"ignored\"}";
        assertDoesNotThrow(() -> WiseApiMapper.sneakyReadValue(json, WiseMoney.class));
    }

    @Test
    void mapper_writesTimestampAsIsoString() {
        WiseApiServerError error = WiseApiServerError.builder()
            .timestamp(ZonedDateTime.parse("2024-01-15T10:30:00Z"))
            .build();
        String json = WiseApiMapper.sneakyWriteAsString(error);
        assertThat(json).contains("2024-01-15");
        assertThat(json).doesNotContainPattern("\"timestamp\":\\d{13}");
    }

    @Test
    void mapper_readsTimestampFromIsoString() {
        String json = "{\"timestamp\":\"2024-01-15T10:30:00Z\"}";
        WiseApiServerError error = WiseApiMapper.sneakyReadValue(json, WiseApiServerError.class);
        assertNotNull(error.getTimestamp());
    }

    @Test
    void privateConstructor_throwsUnsupportedOperationException() throws Exception {
        Constructor<WiseApiMapper> constructor = WiseApiMapper.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        InvocationTargetException thrown = assertThrows(InvocationTargetException.class, constructor::newInstance);
        assertThat(thrown.getCause()).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void getMapper_returnsObjectMapper() {
        assertThat(WiseApiMapper.getMapper()).isInstanceOf(ObjectMapper.class);
    }

}
