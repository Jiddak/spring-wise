package com.jid.springwise.webhook.model;

import lombok.*;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetWebhookRequest {
    private long profileId;
    private String subscriptionId;
}
