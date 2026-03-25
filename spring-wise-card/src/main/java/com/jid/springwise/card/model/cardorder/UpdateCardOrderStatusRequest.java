package com.jid.springwise.card.model.cardorder;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class UpdateCardOrderStatusRequest {
    private long profileId;
    private String cardOrderId;
    private WiseCardOrderStatusUpdate statusUpdate;
}
