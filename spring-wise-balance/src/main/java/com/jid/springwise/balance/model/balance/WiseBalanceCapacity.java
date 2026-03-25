package com.jid.springwise.balance.model.balance;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseBalanceCapacity {
    @JsonProperty("hasLimit")     private Boolean hasLimit;
    @JsonProperty("depositLimit") private WiseDepositLimit depositLimit;
}
