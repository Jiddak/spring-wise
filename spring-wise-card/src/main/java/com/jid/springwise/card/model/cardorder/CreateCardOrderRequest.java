package com.jid.springwise.card.model.cardorder;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class CreateCardOrderRequest {
    private long profileId;
    private WiseCardOrderCreate cardOrder;
}
