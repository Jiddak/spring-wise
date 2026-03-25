package com.jid.springwise.balance.model.balance;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jid.springwise.core.model.WiseMoney;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseBalanceMovement {

    @JsonProperty("quoteId")
    private String quoteId;

    @JsonProperty("sourceBalanceId")
    private Long sourceBalanceId;

    @JsonProperty("targetBalanceId")
    private Long targetBalanceId;

    @JsonProperty("amount")
    private WiseMoney amount;

}
