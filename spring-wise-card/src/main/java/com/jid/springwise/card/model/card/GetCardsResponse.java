package com.jid.springwise.card.model.card;
import lombok.*;
import java.util.List;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetCardsResponse {
    private List<WiseCard> cards;
}
