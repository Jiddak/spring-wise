package com.jid.springwise.card.model.card;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseCardStatusUpdate {
    @JsonProperty("status") private WiseCardStatus status;
    @JsonProperty("token")  private String token;
}
