package com.jid.springwise.balance.model.balance;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jid.springwise.core.model.WiseMoney;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseBalanceMovementResponse {
    @JsonProperty("id")            private Long id;
    @JsonProperty("type")          private String type;
    @JsonProperty("state")         private String state;
    @JsonProperty("balancesAfter") private List<WiseBalanceAfter> balancesAfter;
    @JsonProperty("creationTime")  private String creationTime;
    @JsonProperty("sourceAmount")  private WiseMoney sourceAmount;
    @JsonProperty("targetAmount")  private WiseMoney targetAmount;
    @JsonProperty("rate")          private BigDecimal rate;
    @JsonProperty("feeAmounts")    private List<WiseMoney> feeAmounts;
    @JsonProperty("steps")         private List<WiseBalanceMovementStep> steps;
}
