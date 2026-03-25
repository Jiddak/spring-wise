package com.jid.springwise.card.model.spendcontrols;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class DeleteSpendControlRequest {
    private long profileId;
    private String spendControlId;
}
