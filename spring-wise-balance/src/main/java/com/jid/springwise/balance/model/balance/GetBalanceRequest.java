package com.jid.springwise.balance.model.balance;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetBalanceRequest {
    private long profileId;
    private long balanceId;
}
