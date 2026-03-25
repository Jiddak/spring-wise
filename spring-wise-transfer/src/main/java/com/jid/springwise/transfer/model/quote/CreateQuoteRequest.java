package com.jid.springwise.transfer.model.quote;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class CreateQuoteRequest {
    private long profileId;
    private WiseQuoteCreate quote;
}
