package com.jid.springwise.webhook.model;

import lombok.*;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class CreateWebhookResponse {
    private WiseWebhookDetails webhook;
}
