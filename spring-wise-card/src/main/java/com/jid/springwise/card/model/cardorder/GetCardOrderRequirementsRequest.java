package com.jid.springwise.card.model.cardorder;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetCardOrderRequirementsRequest {
    private long profileId;
    private String cardOrderId;
}
