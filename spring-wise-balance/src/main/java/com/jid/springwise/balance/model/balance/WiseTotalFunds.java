package com.jid.springwise.balance.model.balance;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jid.springwise.core.model.WiseMoney;
import lombok.*;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseTotalFunds {
    @JsonProperty("totalWorth")     private WiseMoney totalWorth;
    @JsonProperty("totalAvailable") private WiseMoney totalAvailable;
    @JsonProperty("totalCash")      private WiseMoney totalCash;
    @JsonProperty("overdraft")      private WiseOverdraft overdraft;
}
