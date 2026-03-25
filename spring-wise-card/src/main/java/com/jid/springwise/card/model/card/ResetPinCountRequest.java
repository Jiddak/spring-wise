package com.jid.springwise.card.model.card;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class ResetPinCountRequest {
    private long profileId;
    private String cardToken;
}
