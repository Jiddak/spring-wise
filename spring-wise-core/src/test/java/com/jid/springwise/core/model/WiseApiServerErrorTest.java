package com.jid.springwise.core.model;

import com.jid.springwise.core.WiseApiMapper;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WiseApiServerErrorTest {

    @Test
    void builder_setsAllFields() {
        ZonedDateTime timestamp = ZonedDateTime.parse("2024-01-15T10:30:00Z");
        WiseApiServerError error = WiseApiServerError.builder()
            .error("Internal Server Error")
            .message("An unexpected error occurred")
            .path("/v1/resource")
            .status(500)
            .timestamp(timestamp)
            .build();

        assertEquals("Internal Server Error", error.getError());
        assertEquals("An unexpected error occurred", error.getMessage());
        assertEquals("/v1/resource", error.getPath());
        assertEquals(500, error.getStatus());
        assertEquals(timestamp, error.getTimestamp());
    }

    @Test
    void noArgConstructor_allFieldsNull() {
        WiseApiServerError error = new WiseApiServerError();
        assertNull(error.getMessage());
        assertNull(error.getStatus());
    }

    @Test
    void equality_basedOnFields() {
        WiseApiServerError a = WiseApiServerError.builder().error("Not Found").status(404).build();
        WiseApiServerError b = WiseApiServerError.builder().error("Not Found").status(404).build();
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void serialisation_usesJsonPropertyNames() throws Exception {
        WiseApiServerError error = WiseApiServerError.builder()
            .error("Not Found")
            .status(404)
            .build();
        String json = WiseApiMapper.sneakyWriteAsString(error);
        assertThat(json).contains("\"error\":\"Not Found\"");
        assertThat(json).contains("\"status\":404");
    }

    @Test
    void serialisation_excludesNullFields() throws Exception {
        WiseApiServerError error = WiseApiServerError.builder().status(500).build();
        String json = WiseApiMapper.sneakyWriteAsString(error);
        assertThat(json).doesNotContain("\"message\"");
        assertThat(json).doesNotContain("\"path\"");
        assertThat(json).doesNotContain("\"error\"");
    }

    @Test
    void deserialisation_mapsJsonPropertyNames() throws Exception {
        String json = "{\"error\":\"Not Found\",\"message\":\"Resource not found\",\"path\":\"/v1/test\",\"status\":404}";
        WiseApiServerError error = WiseApiMapper.getMapper().readValue(json, WiseApiServerError.class);
        assertEquals("Not Found", error.getError());
        assertEquals("Resource not found", error.getMessage());
        assertEquals("/v1/test", error.getPath());
        assertEquals(404, error.getStatus());
    }

    @Test
    void deserialisation_mapsTimestampFromIsoString() throws Exception {
        String json = "{\"status\":500,\"timestamp\":\"2024-01-15T10:30:00Z\"}";
        WiseApiServerError error = WiseApiMapper.getMapper().readValue(json, WiseApiServerError.class);
        assertNotNull(error.getTimestamp());
        assertEquals(2024, error.getTimestamp().getYear());
    }

    @Test
    void setters_updateFields() {
        WiseApiServerError error = new WiseApiServerError();
        error.setStatus(503);
        error.setMessage("Service unavailable");
        assertEquals(503, error.getStatus());
        assertEquals("Service unavailable", error.getMessage());
    }

}
