package com.jid.springwise.card.model.card;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetCardRequest {
    private long profileId;
    private String cardToken;
}
