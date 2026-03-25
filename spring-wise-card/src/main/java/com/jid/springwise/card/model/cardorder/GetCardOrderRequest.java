package com.jid.springwise.card.model.cardorder;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetCardOrderRequest {
    private long profileId;
    private String cardOrderId;
}
