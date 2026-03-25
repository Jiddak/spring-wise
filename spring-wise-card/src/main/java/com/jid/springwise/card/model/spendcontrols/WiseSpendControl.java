package com.jid.springwise.card.model.spendcontrols;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.util.List;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseSpendControl {
    @JsonProperty("id")         private String id;
    @JsonProperty("profile")    private Long profile;
    @JsonProperty("type")       private String type;
    @JsonProperty("value")      private Object value;
    @JsonProperty("cardTokens") private List<String> cardTokens;
}
