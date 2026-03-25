package com.jid.springwise.card.model.cardtransaction;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetCardTransactionRequest {
    private long profileId;
    private String transactionId;
}
