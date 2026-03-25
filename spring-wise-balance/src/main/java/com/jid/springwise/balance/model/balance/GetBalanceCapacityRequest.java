package com.jid.springwise.balance.model.balance;

import lombok.*;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetBalanceCapacityRequest {
    private long profileId;
    private String currency;
}
