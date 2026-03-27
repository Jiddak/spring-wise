package com.jid.springwise.core;

import com.jid.springwise.core.exception.WiseApiException;
import com.jid.springwise.core.exception.WiseClientApiException;
import com.jid.springwise.core.exception.WiseServerApiException;
import com.jid.springwise.core.model.WiseApiClientError;
import com.jid.springwise.core.model.WiseApiServerError;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class DefaultWiseApiErrorHandler implements WiseApiErrorHandler {

    private static final Logger log = LoggerFactory.getLogger(DefaultWiseApiErrorHandler.class);

    private final ObjectMapper objectMapper;

    public DefaultWiseApiErrorHandler() {
        this.objectMapper = WiseApiMapper.getMapper();
    }

    public DefaultWiseApiErrorHandler(WiseApiConfig config) {
        this.objectMapper = config.getObjectMapper();
    }

    @Override
    public void handleDefault(ClientHttpRequest request, ClientHttpResponse response) throws IOException {

        String body = new String(response.getBody().readAllBytes(), StandardCharsets.UTF_8);

        List<WiseApiClientError> clientErrors = mapToClientError(body);
        if (clientErrors != null) {
            throw new WiseClientApiException(clientErrors);
        }

        WiseApiServerError serverError = mapToServerError(body);
        if (serverError != null) {
            throw new WiseServerApiException(serverError);
        }

        throw new WiseApiException("Unknown error: " + body);

    }

    private List<WiseApiClientError> mapToClientError(String body) {

        try {
            return objectMapper.readValue(body, new TypeReference<List<WiseApiClientError>>() {});
        } catch (Exception e) {
            log.debug("Unable to map response to List<WiseApiClientError>: {}", body);
            return null;
        }

    }

    private WiseApiServerError mapToServerError(String body) {

        try {
            return objectMapper.readValue(body, WiseApiServerError.class);
        } catch (Exception e) {
            log.debug("Unable to map response to WiseApiServerError: {}", body);
            return null;
        }

    }

}
