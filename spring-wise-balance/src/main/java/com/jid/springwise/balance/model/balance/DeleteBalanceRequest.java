package com.jid.springwise.balance.model.balance;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class DeleteBalanceRequest {
    private long profileId;
    private long balanceId;
}
