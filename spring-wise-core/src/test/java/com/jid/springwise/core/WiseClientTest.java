package com.jid.springwise.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class WiseClientTest {

    @Test
    void constructor_buildsRestClient() {
        WiseApiConfig config = WiseApiConfig.builder()
            .baseUrl("https://api.wise.com")
            .apiToken("test-token")
            .build();

        WiseClient client = new WiseClient(config);
        assertNotNull(client.getRestClient());
    }

    @Test
    void constructor_withCustomErrorHandler_buildsRestClient() {
        WiseApiErrorHandler customHandler = mock(WiseApiErrorHandler.class);
        WiseApiConfig config = WiseApiConfig.builder()
            .baseUrl("https://api.wise.com")
            .apiToken("test-token")
            .errorHandler(customHandler)
            .build();

        WiseClient client = new WiseClient(config);
        assertNotNull(client.getRestClient());
    }

    @Test
    void constructor_defaultErrorHandlerIsDefaultWiseApiErrorHandler() {
        WiseApiConfig config = WiseApiConfig.builder()
            .baseUrl("https://api.wise.com")
            .apiToken("test-token")
            .build();

        new WiseClient(config);
        assertInstanceOf(DefaultWiseApiErrorHandler.class, config.getErrorHandler());
    }

}
