package com.jid.springwise.balance.model.balance;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateBalanceRequest {

    private long profileId;

    @Builder.Default
    private String idempotenceUuid = UUID.randomUUID().toString();

    private WiseBalanceCreate balance;

}
