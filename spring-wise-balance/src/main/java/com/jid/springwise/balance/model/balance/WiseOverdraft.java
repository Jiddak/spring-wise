package com.jid.springwise.balance.model.balance;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jid.springwise.core.model.WiseMoney;
import lombok.*;
import java.util.List;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseOverdraft {
    @JsonProperty("limit")               private WiseMoney limit;
    @JsonProperty("used")                private WiseMoney used;
    @JsonProperty("available")           private WiseMoney available;
    @JsonProperty("availableByCurrency") private List<WiseMoney> availableByCurrency;
}
