package com.jid.springwise.card.model.card;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class UpdateSpendingPermissionsResponse {
    private WiseCardSpendingPermissions permissions;
}
