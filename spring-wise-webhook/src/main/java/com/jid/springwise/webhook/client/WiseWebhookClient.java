package com.jid.springwise.webhook.client;

import com.jid.springwise.core.WiseClient;
import com.jid.springwise.core.WiseApiConfig;
import com.jid.springwise.webhook.model.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

/**
 * Client for the Webhook (Subscription) API.
 */
public class WiseWebhookClient {

    private final RestClient restClient;

    public WiseWebhookClient(WiseClient wiseClient) {
        this.restClient = wiseClient.getRestClient();
    }

    public WiseWebhookClient(WiseApiConfig config) {
        this(new WiseClient(config));
    }

    /**
     * Creates a webhook subscription for a profile.
     */
    public CreateWebhookResponse createWebhook(CreateWebhookRequest request) {
        WiseWebhookDetails details = restClient.post()
            .uri("v3/profiles/{profileId}/subscriptions",
                Map.of("profileId", request.getProfileId()))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .body(request.getWebhook())
            .retrieve()
            .toEntity(WiseWebhookDetails.class)
            .getBody();
        return CreateWebhookResponse.builder().webhook(details).build();
    }

    /**
     * Lists all webhook subscriptions for a profile.
     */
    public GetWebhooksResponse getWebhooks(GetWebhooksRequest request) {
        List<WiseWebhookDetails> list = restClient.get()
            .uri("v3/profiles/{profileId}/subscriptions",
                Map.of("profileId", request.getProfileId()))
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .toEntity(new ParameterizedTypeReference<List<WiseWebhookDetails>>() {})
            .getBody();
        return GetWebhooksResponse.builder().webhooks(list).build();
    }

    /**
     * Retrieves a specific webhook subscription.
     */
    public GetWebhookResponse getWebhook(GetWebhookRequest request) {
        WiseWebhookDetails details = restClient.get()
            .uri("v3/profiles/{profileId}/subscriptions/{subscriptionId}",
                Map.of("profileId", request.getProfileId(),
                       "subscriptionId", request.getSubscriptionId()))
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .toEntity(WiseWebhookDetails.class)
            .getBody();
        return GetWebhookResponse.builder().webhook(details).build();
    }

    /**
     * Deletes a webhook subscription.
     */
    public DeleteWebhookResponse deleteWebhook(DeleteWebhookRequest request) {
        restClient.delete()
            .uri("v3/profiles/{profileId}/subscriptions/{subscriptionId}",
                Map.of("profileId", request.getProfileId(),
                       "subscriptionId", request.getSubscriptionId()))
            .retrieve()
            .toBodilessEntity();
        return DeleteWebhookResponse.builder().success(true).build();
    }
}
