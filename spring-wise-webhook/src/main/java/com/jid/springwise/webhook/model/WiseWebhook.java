package com.jid.springwise.webhook.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/** Wire body sent to the Wise API when creating a webhook subscription. */
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseWebhook {
    @JsonProperty("name")      private String name;
    @JsonProperty("triggerOn") private String triggerOn;
    @JsonProperty("delivery")  private WiseWebhookDelivery delivery;
}
