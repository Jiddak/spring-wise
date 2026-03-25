package com.jid.springwise.transfer.model.rate;
import lombok.*;
import java.util.List;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetRatesResponse {
    private List<WiseRate> rates;
}
