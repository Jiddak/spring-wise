package com.jid.springwise.transfer.model.rate;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetRatesRequest {
    private String source;
    private String target;
}
