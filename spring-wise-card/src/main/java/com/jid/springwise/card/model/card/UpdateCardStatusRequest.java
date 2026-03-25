package com.jid.springwise.card.model.card;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class UpdateCardStatusRequest {
    private long profileId;
    private String cardToken;
    private WiseCardStatusUpdate statusUpdate;
}
