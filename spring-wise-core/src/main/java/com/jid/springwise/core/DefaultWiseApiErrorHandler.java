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
import org.springframework.http.HttpRequest;
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
    public void handleDefault(HttpRequest request, ClientHttpResponse response) {

        try {
            String body = new String(response.getBody().readAllBytes(), StandardCharsets.UTF_8);

            mapToClientError(body);
            mapToServerError(body);

            throw new WiseApiException("Unknown error: " + body);

        } catch (IOException e) {
            throw new WiseApiException("Unabke to read response body",e);
        }



    }

    private void mapToClientError(String body) {

        try {
            List<WiseApiClientError> clientErrors = objectMapper.readValue(body, new TypeReference<>() {});
            throw new WiseClientApiException(clientErrors);
        } catch (Exception e) {
            log.debug("Unable to map response to List<WiseApiClientError>: {}", body);
        }

    }

    private void mapToServerError(String body) {

        try {
            WiseApiServerError serverError = objectMapper.readValue(body, WiseApiServerError.class);
            throw new WiseServerApiException(serverError);
        } catch (Exception e) {
            log.debug("Unable to map response to WiseApiServerError: {}", body);
        }

    }

}
