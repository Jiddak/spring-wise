package com.jid.springwise.balance.model.statement;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jid.springwise.core.model.WiseMoney;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseStatementTransactionDetails {

    @JsonProperty("type")
    private String type;

    @JsonProperty("description")
    private String description;

    @JsonProperty("amount")
    private WiseMoney amount;

    @JsonProperty("senderName")
    private String senderName;

    @JsonProperty("senderAccount")
    private String senderAccount;

    @JsonProperty("paymentReference")
    private String paymentReference;

    @JsonProperty("category")
    private String category;

    @JsonProperty("merchant")
    private WiseStatementMerchant merchant;

    @JsonProperty("sourceAmount")
    private WiseMoney sourceAmount;

    @JsonProperty("targetAmount")
    private WiseMoney targetAmount;

    @JsonProperty("fee")
    private WiseMoney fee;

    @JsonProperty("rate")
    private BigDecimal rate;

}
