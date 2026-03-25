package com.jid.springwise.balance.model.balance;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class MoveBalanceFundsRequest {
    private long profileId;
    private String idempotenceUuid;
    private WiseBalanceMovement balanceMovement;
}
