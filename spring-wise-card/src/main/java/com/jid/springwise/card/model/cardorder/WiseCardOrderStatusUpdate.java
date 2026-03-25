package com.jid.springwise.card.model.cardorder;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseCardOrderStatusUpdate {
    @JsonProperty("status") private WiseCardOrderStatus status;
}
