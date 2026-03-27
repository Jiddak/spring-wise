package com.jid.springwise.core;

import lombok.Getter;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClient;

/**
 * Core HTTP client shared by all Wise API client modules.
 * Construct once and pass to any number of domain clients.
 */
public class WiseClient {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Getter
    private final RestClient restClient;

    public WiseClient(WiseApiConfig config) {

        CloseableHttpClient httpClient = HttpClients.custom()
            .setConnectionManager(PoolingHttpClientConnectionManagerBuilder.create().build())
            .build();

        HttpComponentsClientHttpRequestFactory requestFactory =
            new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);

         this.restClient = RestClient.builder()
            .requestFactory(requestFactory)
            .baseUrl(config.getBaseUrl())
            .defaultHeader(AUTHORIZATION_HEADER, "Bearer " + config.getApiToken())
             .defaultStatusHandler(status -> !status.is2xxSuccessful(), config.getErrorHandler()::handleError)
            .messageConverters(converters ->
                converters.add(new MappingJackson2HttpMessageConverter(config.getObjectMapper())))
             .build();

    }

}
