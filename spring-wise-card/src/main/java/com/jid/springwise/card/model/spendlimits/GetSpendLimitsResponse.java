package com.jid.springwise.card.model.spendlimits;
import lombok.*;
import java.util.List;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetSpendLimitsResponse {
    private List<WiseSpendLimit> spendLimits;
}
