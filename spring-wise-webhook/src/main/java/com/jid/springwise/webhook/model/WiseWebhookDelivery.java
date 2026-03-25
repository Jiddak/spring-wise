package com.jid.springwise.webhook.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseWebhookDelivery {
    @JsonProperty("version") private String version;
    @JsonProperty("url")     private String url;
}
