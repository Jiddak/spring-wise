package com.jid.springwise.card.model.spendcontrols;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.util.List;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseSpendControlCreate {
    @JsonProperty("type")       private String type;
    @JsonProperty("value")      private Object value;
    @JsonProperty("cardTokens") private List<String> cardTokens;
}
