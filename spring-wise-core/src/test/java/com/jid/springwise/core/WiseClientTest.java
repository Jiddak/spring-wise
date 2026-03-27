package com.jid.springwise.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
    void getRestClient_returnsWiseApiErrorHandler() {
        WiseApiConfig config = WiseApiConfig.builder()
            .baseUrl("https://api.wise.com")
            .apiToken("test-token")
            .build();

        WiseClient client = new WiseClient(config);
        assertThat(config.getErrorHandler()).isInstanceOf(DefaultWiseApiErrorHandler.class);
        assertNotNull(client.getRestClient());
    }

}
