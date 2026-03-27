package com.jid.springwise.core;

import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class DefaultWiseApiErrorHandler implements WiseApiErrorHandler {
    @Override
    public void handleDefault(ClientHttpRequest request, ClientHttpResponse response) throws IOException {

    }
}
