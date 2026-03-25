package com.jid.springwise.card.model.cardkiosk;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class ProduceCardRequest {
    private long profileId;
    private String cardToken;
    private WiseCardProductionCreate production;
}
