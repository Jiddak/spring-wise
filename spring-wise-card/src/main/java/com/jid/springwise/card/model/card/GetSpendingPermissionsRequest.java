package com.jid.springwise.card.model.card;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetSpendingPermissionsRequest {
    private long profileId;
    private String cardToken;
}
