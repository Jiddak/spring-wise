package com.jid.springwise.card.model.spendlimits;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jid.springwise.core.model.WiseMoney;
import lombok.*;
import java.util.List;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseSpendLimit {
    @JsonProperty("id")         private String id;
    @JsonProperty("profile")    private Long profile;
    @JsonProperty("type")       private String type;
    @JsonProperty("limit")      private WiseMoney limit;
    @JsonProperty("interval")   private String interval;
    @JsonProperty("cardTokens") private List<String> cardTokens;
}
