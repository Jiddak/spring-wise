package com.jid.springwise.card.model.card;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class UpdateSpendingPermissionsRequest {
    private long profileId;
    private String cardToken;
    private WiseCardSpendingPermissions permissions;
}
