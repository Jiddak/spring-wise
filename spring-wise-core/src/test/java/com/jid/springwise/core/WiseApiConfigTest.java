package com.jid.springwise.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class WiseApiConfigTest {

    @Test
    void builder_setsBaseUrlAndApiToken() {
        WiseApiConfig config = WiseApiConfig.builder()
            .baseUrl("https://api.wise.com")
            .apiToken("test-token")
            .build();

        assertEquals("https://api.wise.com", config.getBaseUrl());
        assertEquals("test-token", config.getApiToken());
    }

    @Test
    void builder_defaultObjectMapper_isWiseApiMapperInstance() {
        WiseApiConfig config = WiseApiConfig.builder()
            .baseUrl("https://api.wise.com")
            .apiToken("token")
            .build();

        assertNotNull(config.getObjectMapper());
        assertSame(WiseApiMapper.getMapper(), config.getObjectMapper());
    }

    @Test
    void builder_defaultErrorHandler_isDefaultWiseApiErrorHandler() {
        WiseApiConfig config = WiseApiConfig.builder()
            .baseUrl("https://api.wise.com")
            .apiToken("token")
            .build();

        assertNotNull(config.getErrorHandler());
        assertThat(config.getErrorHandler()).isInstanceOf(DefaultWiseApiErrorHandler.class);
    }

    @Test
    void builder_customObjectMapper_overridesDefault() {
        ObjectMapper customMapper = new ObjectMapper();
        WiseApiConfig config = WiseApiConfig.builder()
            .baseUrl("https://api.wise.com")
            .apiToken("token")
            .objectMapper(customMapper)
            .build();

        assertSame(customMapper, config.getObjectMapper());
    }

    @Test
    void builder_customErrorHandler_overridesDefault() {
        WiseApiErrorHandler customHandler = mock(WiseApiErrorHandler.class);
        WiseApiConfig config = WiseApiConfig.builder()
            .baseUrl("https://api.wise.com")
            .apiToken("token")
            .errorHandler(customHandler)
            .build();

        assertSame(customHandler, config.getErrorHandler());
    }

    @Test
    void toString_excludesApiToken() {
        WiseApiConfig config = WiseApiConfig.builder()
            .baseUrl("https://api.wise.com")
            .apiToken("super-secret-token")
            .build();

        String str = config.toString();
        assertThat(str).doesNotContain("super-secret-token");
        assertThat(str).contains("https://api.wise.com");
    }

}
