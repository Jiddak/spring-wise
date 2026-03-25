package com.jid.springwise.webhook.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseWebhookCreatedBy {
    /** Always "application" for application-level webhooks. */
    @JsonProperty("type") private String type;
    /** Client ID of the creator. Not always the same as the client key. */
    @JsonProperty("id")   private String id;
}
