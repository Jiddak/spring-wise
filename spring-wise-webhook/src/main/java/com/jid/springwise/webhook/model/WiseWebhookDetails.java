package com.jid.springwise.webhook.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/** Webhook subscription as returned by the Wise API. */
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseWebhookDetails {
    @JsonProperty("id")        private String id;
    @JsonProperty("name")      private String name;
    @JsonProperty("delivery")  private WiseWebhookDelivery delivery;
    @JsonProperty("triggerOn") private String triggerOn;
    @JsonProperty("scope")     private WiseWebhookScope scope;
    @JsonProperty("createdBy") private WiseWebhookCreatedBy createdBy;
    @JsonProperty("createdAt") private String createdAt;
}
