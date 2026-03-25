package com.jid.springwise.card.model.spendlimits;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class DeleteSpendLimitRequest {
    private long profileId;
    private String spendLimitId;
}
