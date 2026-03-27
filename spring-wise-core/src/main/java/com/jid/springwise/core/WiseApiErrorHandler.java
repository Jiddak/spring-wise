package com.jid.springwise.core;

import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public interface WiseApiErrorHandler {

    default void handlerError(ClientHttpRequest request, ClientHttpResponse response) throws IOException {
        switch (response.getStatusCode().value()) {
            case 400 -> handle400(request, response);
            case 401 -> handle401(request, response);
            case 403 -> handle403(request, response);
            case 404 -> handle404(request, response);
            case 408 -> handle408(request, response);
            case 422 -> handle422(request, response);
            case 429 -> handle429(request, response);
            case 500 -> handle500(request, response);
            default -> handleDefault(request, response);
        }
    }

    default void handle400(ClientHttpRequest request, ClientHttpResponse response) throws IOException {
        handleDefault(request, response);
    }

    default void handle401(ClientHttpRequest request, ClientHttpResponse response) throws IOException {
        handleDefault(request, response);
    }

    default void handle403(ClientHttpRequest request, ClientHttpResponse response) throws IOException {
        handleDefault(request, response);
    }

    default void handle404(ClientHttpRequest request, ClientHttpResponse response) throws IOException {
        handleDefault(request, response);
    }

    default void handle408(ClientHttpRequest request, ClientHttpResponse response) throws IOException {
        handleDefault(request, response);
    }

    default void handle422(ClientHttpRequest request, ClientHttpResponse response) throws IOException {
        handleDefault(request, response);
    }

    default void handle429(ClientHttpRequest request, ClientHttpResponse response) throws IOException {
        handleDefault(request, response);
    }

    default void handle500(ClientHttpRequest request, ClientHttpResponse response) throws IOException {
        handleDefault(request, response);
    }

    void handleDefault(ClientHttpRequest request, ClientHttpResponse response) throws IOException;

}
