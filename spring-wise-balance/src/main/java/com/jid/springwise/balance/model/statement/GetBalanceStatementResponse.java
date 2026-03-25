package com.jid.springwise.balance.model.statement;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetBalanceStatementResponse {
    private WiseBalanceStatement statement;
}
