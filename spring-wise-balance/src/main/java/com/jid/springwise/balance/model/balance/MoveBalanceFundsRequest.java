package com.jid.springwise.balance.model.balance;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MoveBalanceFundsRequest {

    private long profileId;

    @Builder.Default
    private String idempotenceUuid = UUID.randomUUID().toString();

    private WiseBalanceMovement balanceMovement;

}
