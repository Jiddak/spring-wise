package com.jid.springwise.transfer.model.quote;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class UpdateQuoteRequest {
    private long profileId;
    private String quoteId;
    private WiseQuoteCreate quote;
}
