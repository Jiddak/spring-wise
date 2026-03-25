package com.jid.springwise.card.model.spendcontrols;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class CreateSpendControlRequest {
    private long profileId;
    private WiseSpendControlCreate spendControl;
}
