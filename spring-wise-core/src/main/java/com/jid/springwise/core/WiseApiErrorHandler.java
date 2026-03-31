package com.jid.springwise.core;

import com.jid.springwise.core.exception.WiseApiException;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpResponse;

public interface WiseApiErrorHandler {

    default void handleError(HttpRequest request, ClientHttpResponse response) {
        try {
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
        } catch (WiseApiException e) {
            throw e;
        } catch (Exception e) {
            throw new WiseApiException(e);
        }
    }

    default void handle400(HttpRequest request, ClientHttpResponse response) {
        handleDefault(request, response);
    }

    default void handle401(HttpRequest request, ClientHttpResponse response) {
        handleDefault(request, response);
    }

    default void handle403(HttpRequest request, ClientHttpResponse response) {
        handleDefault(request, response);
    }

    default void handle404(HttpRequest request, ClientHttpResponse response) {
        handleDefault(request, response);
    }

    default void handle408(HttpRequest request, ClientHttpResponse response) {
        handleDefault(request, response);
    }

    default void handle422(HttpRequest request, ClientHttpResponse response) {
        handleDefault(request, response);
    }

    default void handle429(HttpRequest request, ClientHttpResponse response) {
        handleDefault(request, response);
    }

    default void handle500(HttpRequest request, ClientHttpResponse response) {
        handleDefault(request, response);
    }

    void handleDefault(HttpRequest request, ClientHttpResponse response);
}
