package com.jid.springwise.balance.model.statement;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jid.springwise.core.model.WiseMoney;
import lombok.*;
import java.time.ZonedDateTime;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseStatementTransaction {
    @JsonProperty("type")            private String type;
    @JsonProperty("date")            private ZonedDateTime date;
    @JsonProperty("amount")          private WiseMoney amount;
    @JsonProperty("totalFees")       private WiseMoney totalFees;
    @JsonProperty("details")         private WiseStatementTransactionDetails details;
    @JsonProperty("exchangeDetails") private WiseStatementExchangeDetails exchangeDetails;
    @JsonProperty("runningBalance")  private WiseMoney runningBalance;
    @JsonProperty("referenceNumber") private String referenceNumber;
}
