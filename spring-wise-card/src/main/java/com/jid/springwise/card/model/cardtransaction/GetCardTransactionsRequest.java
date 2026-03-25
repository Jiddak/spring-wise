package com.jid.springwise.card.model.cardtransaction;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetCardTransactionsRequest {
    private long profileId;
}
