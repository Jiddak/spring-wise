package com.jid.springwise.card.model.cardkiosk;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetCardProductionRequest {
    private long profileId;
    private String cardToken;
}
