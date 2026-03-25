package com.jid.springwise.card.model.cardtransaction;
import lombok.*;
import java.util.List;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetCardTransactionsResponse {
    private List<WiseCardTransaction> transactions;
}
