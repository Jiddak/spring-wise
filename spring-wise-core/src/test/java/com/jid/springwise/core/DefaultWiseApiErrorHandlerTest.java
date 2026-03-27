package com.jid.springwise.core;

import com.jid.springwise.core.exception.WiseApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultWiseApiErrorHandlerTest {

    @Mock HttpRequest request;
    @Mock ClientHttpResponse response;

    private DefaultWiseApiErrorHandler handler;

    @BeforeEach
    void setUp() {
        handler = new DefaultWiseApiErrorHandler();
    }

    // --- constructors ---

    @Test
    void noArgConstructor_createsHandler() {
        assertNotNull(new DefaultWiseApiErrorHandler());
    }

    @Test
    void configConstructor_createsHandlerWithConfigMapper() {
        WiseApiConfig config = WiseApiConfig.builder()
            .baseUrl("https://api.wise.com")
            .apiToken("token")
            .build();
        assertNotNull(new DefaultWiseApiErrorHandler(config));
    }

    // --- handleDefault: client error JSON body ---

    @Test
    void handleDefault_withClientErrorJsonArray_throwsUnknownWiseApiException() throws IOException {
        String body = "[{\"code\":\"400\",\"message\":\"Bad request\",\"path\":\"/v1/test\"}]";
        when(response.getBody()).thenReturn(new ByteArrayInputStream(body.getBytes(StandardCharsets.UTF_8)));

        WiseApiException ex = assertThrows(WiseApiException.class, () -> handler.handleDefault(request, response));
        assertEquals("Unknown error: " + body, ex.getMessage());
        assertNull(ex.getCause());
    }

    // --- handleDefault: server error JSON body ---

    @Test
    void handleDefault_withServerErrorJsonObject_throwsUnknownWiseApiException() throws IOException {
        String body = "{\"error\":\"Not Found\",\"status\":404,\"message\":\"Resource not found\",\"path\":\"/v1/test\"}";
        when(response.getBody()).thenReturn(new ByteArrayInputStream(body.getBytes(StandardCharsets.UTF_8)));

        WiseApiException ex = assertThrows(WiseApiException.class, () -> handler.handleDefault(request, response));
        assertEquals("Unknown error: " + body, ex.getMessage());
        assertNull(ex.getCause());
    }

    // --- handleDefault: unrecognised body ---

    @Test
    void handleDefault_withPlainTextBody_throwsUnknownWiseApiException() throws IOException {
        String body = "unexpected plain text error";
        when(response.getBody()).thenReturn(new ByteArrayInputStream(body.getBytes(StandardCharsets.UTF_8)));

        WiseApiException ex = assertThrows(WiseApiException.class, () -> handler.handleDefault(request, response));
        assertEquals("Unknown error: " + body, ex.getMessage());
    }

    @Test
    void handleDefault_withEmptyBody_throwsUnknownWiseApiException() throws IOException {
        String body = "";
        when(response.getBody()).thenReturn(new ByteArrayInputStream(body.getBytes(StandardCharsets.UTF_8)));

        WiseApiException ex = assertThrows(WiseApiException.class, () -> handler.handleDefault(request, response));
        assertEquals("Unknown error: " + body, ex.getMessage());
    }

    // --- handleDefault: IOException reading body ---

    @Test
    void handleDefault_withIOExceptionReadingBody_throwsWiseApiExceptionWithCause() throws IOException {
        IOException ioException = new IOException("Stream closed");
        when(response.getBody()).thenThrow(ioException);

        WiseApiException ex = assertThrows(WiseApiException.class, () -> handler.handleDefault(request, response));
        assertEquals("Unabke to read response body", ex.getMessage());
        assertSame(ioException, ex.getCause());
    }

    // --- implements WiseApiErrorHandler ---

    @Test
    void implementsWiseApiErrorHandler() {
        assertInstanceOf(WiseApiErrorHandler.class, handler);
    }

}
