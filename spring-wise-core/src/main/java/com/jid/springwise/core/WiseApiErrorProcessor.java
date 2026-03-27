package com.jid.springwise.core;

import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public interface WiseApiErrorProcessor {

    void processDefault(ClientHttpRequest request, ClientHttpResponse response) throws IOException;

    default void process400(ClientHttpRequest request, ClientHttpResponse response) throws IOException {
        processDefault(request, response);
    }

    default void process401(ClientHttpRequest request, ClientHttpResponse response) throws IOException {
        processDefault(request, response);
    }

    default void process403(ClientHttpRequest request, ClientHttpResponse response) throws IOException {
        processDefault(request, response);
    }

    default void process404(ClientHttpRequest request, ClientHttpResponse response) throws IOException {
        processDefault(request, response);
    }

    default void process408(ClientHttpRequest request, ClientHttpResponse response) throws IOException {
        processDefault(request, response);
    }

    default void process422(ClientHttpRequest request, ClientHttpResponse response) throws IOException {
        processDefault(request, response);
    }

    default void process429(ClientHttpRequest request, ClientHttpResponse response) throws IOException {
        processDefault(request, response);
    }

    default void process500(ClientHttpRequest request, ClientHttpResponse response) throws IOException {
        processDefault(request, response);
    }

    default void processError(ClientHttpRequest request, ClientHttpResponse response) throws IOException {
        switch (response.getStatusCode().value()) {
            case 400 -> process400(request, response);
            case 401 -> process401(request, response);
            case 403 -> process403(request, response);
            case 404 -> process404(request, response);
            case 408 -> process408(request, response);
            case 422 -> process422(request, response);
            case 429 -> process429(request, response);
            case 500 -> process500(request, response);
            default -> processDefault(request, response);
        }
    }

}
