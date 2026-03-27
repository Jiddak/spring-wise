package com.jid.springwise.core.model;

import com.jid.springwise.core.WiseApiMapper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WiseApiClientErrorTest {

    @Test
    void builder_setsAllFields() {
        WiseApiClientError error = WiseApiClientError.builder()
            .timestamp("2024-01-15T10:30:00Z")
            .code("400")
            .path("/v1/resource")
            .message("Bad request")
            .arguments(List.of("field1", "field2"))
            .error("VALIDATION_ERROR")
            .errorDescription("Field validation failed")
            .status("400")
            .build();

        assertEquals("2024-01-15T10:30:00Z", error.getTimestamp());
        assertEquals("400", error.getCode());
        assertEquals("/v1/resource", error.getPath());
        assertEquals("Bad request", error.getMessage());
        assertEquals(List.of("field1", "field2"), error.getArguments());
        assertEquals("VALIDATION_ERROR", error.getError());
        assertEquals("Field validation failed", error.getErrorDescription());
        assertEquals("400", error.getStatus());
    }

    @Test
    void noArgConstructor_allFieldsNull() {
        WiseApiClientError error = new WiseApiClientError();
        assertNull(error.getMessage());
        assertNull(error.getCode());
    }

    @Test
    void equality_basedOnFields() {
        WiseApiClientError a = WiseApiClientError.builder().code("400").message("Bad request").build();
        WiseApiClientError b = WiseApiClientError.builder().code("400").message("Bad request").build();
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void serialisation_usesJsonPropertyNames() throws Exception {
        WiseApiClientError error = WiseApiClientError.builder()
            .code("400")
            .errorDescription("Field invalid")
            .build();
        String json = WiseApiMapper.sneakyWriteAsString(error);
        assertTrue(json.contains("\"code\":\"400\""));
        assertTrue(json.contains("\"error_description\":\"Field invalid\""));
        assertFalse(json.contains("errorDescription"));
    }

    @Test
    void serialisation_excludesNullFields() throws Exception {
        WiseApiClientError error = WiseApiClientError.builder().code("400").build();
        String json = WiseApiMapper.sneakyWriteAsString(error);
        assertFalse(json.contains("\"message\""));
        assertFalse(json.contains("\"path\""));
    }

    @Test
    void deserialisation_mapsJsonPropertyNames() throws Exception {
        String json = "{\"code\":\"400\",\"message\":\"Bad request\",\"error_description\":\"Field invalid\"}";
        WiseApiClientError error = WiseApiMapper.getMapper().readValue(json, WiseApiClientError.class);
        assertEquals("400", error.getCode());
        assertEquals("Bad request", error.getMessage());
        assertEquals("Field invalid", error.getErrorDescription());
    }

    @Test
    void deserialisation_handlesArgumentsArray() throws Exception {
        String json = "{\"code\":\"400\",\"arguments\":[\"field1\",\"field2\"]}";
        WiseApiClientError error = WiseApiMapper.getMapper().readValue(json, WiseApiClientError.class);
        assertEquals(List.of("field1", "field2"), error.getArguments());
    }

}
