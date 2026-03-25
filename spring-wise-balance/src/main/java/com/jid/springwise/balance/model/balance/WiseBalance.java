package com.jid.springwise.balance.model.balance;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jid.springwise.core.model.WiseMoney;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseBalance {
    @JsonProperty("id")               private Long id;
    @JsonProperty("type")             private WiseBalanceType type;
    @JsonProperty("name")             private String name;
    @JsonProperty("amount")           private WiseMoney amount;
    @JsonProperty("reservedAmount")   private WiseMoney reservedAmount;
    @JsonProperty("bankDetails")      private WiseBalanceBankDetails bankDetails;
    @JsonProperty("investmentState")  private WiseInvestmentState investmentState;
    @JsonProperty("creationTime")     private String creationTime;
    @JsonProperty("modificationTime") private String modificationTime;
    @JsonProperty("visible")          private Boolean visible;
    @JsonProperty("primary")          private Boolean primary;
}
