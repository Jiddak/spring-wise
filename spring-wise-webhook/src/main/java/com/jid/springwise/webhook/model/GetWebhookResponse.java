package com.jid.springwise.webhook.model;

import lombok.*;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetWebhookResponse {
    private WiseWebhookDetails webhook;
}
