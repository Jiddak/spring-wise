package com.jid.springwise.webhook.model;

import lombok.*;
import java.util.List;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetWebhooksResponse {
    private List<WiseWebhookDetails> webhooks;
}
