package com.jid.springwise.core;

import com.jid.springwise.core.exception.WiseApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WiseApiErrorHandlerTest {

    @Mock HttpRequest request;
    @Mock ClientHttpResponse response;

    private WiseApiErrorHandler handler;

    @BeforeEach
    void setUp() {
        handler = spy(new WiseApiErrorHandler() {
            @Override
            public void handleDefault(HttpRequest req, ClientHttpResponse res) {
                // no-op: allows routing tests without a real response body
            }
        });
    }

    // --- default handle* methods all delegate to handleDefault ---

    @Test
    void handle400_delegatesToHandleDefault() {
        handler.handle400(request, response);
        verify(handler).handleDefault(request, response);
    }

    @Test
    void handle401_delegatesToHandleDefault() {
        handler.handle401(request, response);
        verify(handler).handleDefault(request, response);
    }

    @Test
    void handle403_delegatesToHandleDefault() {
        handler.handle403(request, response);
        verify(handler).handleDefault(request, response);
    }

    @Test
    void handle404_delegatesToHandleDefault() {
        handler.handle404(request, response);
        verify(handler).handleDefault(request, response);
    }

    @Test
    void handle408_delegatesToHandleDefault() {
        handler.handle408(request, response);
        verify(handler).handleDefault(request, response);
    }

    @Test
    void handle422_delegatesToHandleDefault() {
        handler.handle422(request, response);
        verify(handler).handleDefault(request, response);
    }

    @Test
    void handle429_delegatesToHandleDefault() {
        handler.handle429(request, response);
        verify(handler).handleDefault(request, response);
    }

    @Test
    void handle500_delegatesToHandleDefault() {
        handler.handle500(request, response);
        verify(handler).handleDefault(request, response);
    }

    // --- handleError routes each status code to the correct specific handler ---

    @Test
    void handleError_status400_callsHandle400() throws IOException {
        when(response.getStatusCode()).thenReturn(HttpStatusCode.valueOf(400));
        handler.handleError(request, response);
        verify(handler).handle400(request, response);
    }

    @Test
    void handleError_status401_callsHandle401() throws IOException {
        when(response.getStatusCode()).thenReturn(HttpStatusCode.valueOf(401));
        handler.handleError(request, response);
        verify(handler).handle401(request, response);
    }

    @Test
    void handleError_status403_callsHandle403() throws IOException {
        when(response.getStatusCode()).thenReturn(HttpStatusCode.valueOf(403));
        handler.handleError(request, response);
        verify(handler).handle403(request, response);
    }

    @Test
    void handleError_status404_callsHandle404() throws IOException {
        when(response.getStatusCode()).thenReturn(HttpStatusCode.valueOf(404));
        handler.handleError(request, response);
        verify(handler).handle404(request, response);
    }

    @Test
    void handleError_status408_callsHandle408() throws IOException {
        when(response.getStatusCode()).thenReturn(HttpStatusCode.valueOf(408));
        handler.handleError(request, response);
        verify(handler).handle408(request, response);
    }

    @Test
    void handleError_status422_callsHandle422() throws IOException {
        when(response.getStatusCode()).thenReturn(HttpStatusCode.valueOf(422));
        handler.handleError(request, response);
        verify(handler).handle422(request, response);
    }

    @Test
    void handleError_status429_callsHandle429() throws IOException {
        when(response.getStatusCode()).thenReturn(HttpStatusCode.valueOf(429));
        handler.handleError(request, response);
        verify(handler).handle429(request, response);
    }

    @Test
    void handleError_status500_callsHandle500() throws IOException {
        when(response.getStatusCode()).thenReturn(HttpStatusCode.valueOf(500));
        handler.handleError(request, response);
        verify(handler).handle500(request, response);
    }

    @Test
    void handleError_unknownStatus_routesDirectlyToHandleDefault() throws IOException {
        when(response.getStatusCode()).thenReturn(HttpStatusCode.valueOf(418));
        handler.handleError(request, response);
        verify(handler).handleDefault(request, response);
        verify(handler, never()).handle400(any(), any());
        verify(handler, never()).handle500(any(), any());
    }

    // --- handleError wraps exceptions ---

    @Test
    void handleError_exceptionFromHandler_wrappedInWiseApiException() throws IOException {
        when(response.getStatusCode()).thenReturn(HttpStatusCode.valueOf(400));
        doThrow(new RuntimeException("inner error")).when(handler).handleDefault(any(), any());

        WiseApiException ex = assertThrows(WiseApiException.class, () -> handler.handleError(request, response));
        assertThat(ex.getCause()).isInstanceOf(RuntimeException.class);
        assertThat(ex.getCause().getMessage()).isEqualTo("inner error");
    }

    @Test
    void handleError_ioExceptionFromGetStatusCode_wrappedInWiseApiException() throws IOException {
        when(response.getStatusCode()).thenThrow(new IOException("status read failed"));

        WiseApiException ex = assertThrows(WiseApiException.class, () -> handler.handleError(request, response));
        assertThat(ex.getCause()).isInstanceOf(IOException.class);
        assertThat(ex.getCause().getMessage()).isEqualTo("status read failed");
    }

}
