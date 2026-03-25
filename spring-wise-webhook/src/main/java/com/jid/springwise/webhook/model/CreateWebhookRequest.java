package com.jid.springwise.webhook.model;

import lombok.*;

/** Argument container for createWebhook. */
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class CreateWebhookRequest {
    private long profileId;
    private WiseWebhook webhook;
}
