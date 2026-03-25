package com.jid.springwise.balance.model.balance;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class CreateBalanceRequest {
    private long profileId;
    private String idempotenceUuid;
    private WiseBalanceCreate balance;
}
