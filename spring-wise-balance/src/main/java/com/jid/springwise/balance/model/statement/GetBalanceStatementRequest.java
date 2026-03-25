package com.jid.springwise.balance.model.statement;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetBalanceStatementRequest {
    private long profileId;
    private long balanceId;
    private String intervalStart;
    private String intervalEnd;
}
