package com.jid.springwise.webhook.model;

import lombok.*;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class DeleteWebhookRequest {
    private long profileId;
    private String subscriptionId;
}
