package com.jid.springwise.transfer.model.quote;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class CreateQuoteResponse {
    private WiseQuote quote;
}
