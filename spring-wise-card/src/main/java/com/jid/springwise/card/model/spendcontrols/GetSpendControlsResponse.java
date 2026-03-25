package com.jid.springwise.card.model.spendcontrols;
import lombok.*;
import java.util.List;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetSpendControlsResponse {
    private List<WiseSpendControl> spendControls;
}
