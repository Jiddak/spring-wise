package com.jid.springwise.balance.model.balance;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jid.springwise.core.model.WiseMoney;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseBalanceMovementStep {
    @JsonProperty("id")            private Long id;
    @JsonProperty("type")          private String type;
    @JsonProperty("creationTime")  private String creationTime;
    @JsonProperty("balancesAfter") private List<WiseMoney> balancesAfter;
    @JsonProperty("sourceAmount")  private WiseMoney sourceAmount;
    @JsonProperty("targetAmount")  private WiseMoney targetAmount;
    @JsonProperty("fee")           private WiseMoney fee;
    @JsonProperty("rate")          private BigDecimal rate;
}
