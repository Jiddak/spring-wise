package com.jid.springwise.transfer.model.quote;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetQuoteRequest {
    private long profileId;
    private String quoteId;
}
