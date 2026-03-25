package com.jid.springwise.balance.model.balance;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetBalanceResponse {
    private WiseBalance balance;
}
