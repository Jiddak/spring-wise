package com.jid.springwise.card.model.cardorder;
import lombok.*;
import java.util.List;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetCardOrdersResponse {
    private List<WiseCardOrder> cardOrders;
}
