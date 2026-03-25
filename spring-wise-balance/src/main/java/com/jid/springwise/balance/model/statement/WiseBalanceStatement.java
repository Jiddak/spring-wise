package com.jid.springwise.balance.model.statement;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jid.springwise.core.model.WiseMoney;
import lombok.*;
import java.util.List;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseBalanceStatement {
    @JsonProperty("accountHolder")          private WiseStatementAccountHolder accountHolder;
    @JsonProperty("issuer")                 private WiseStatementIssuer issuer;
    @JsonProperty("bankDetails")            private Object bankDetails;
    @JsonProperty("transactions")           private List<WiseStatementTransaction> transactions;
    @JsonProperty("endOfStatementBalance")  private WiseMoney endOfStatementBalance;
    @JsonProperty("query")                  private WiseStatementQuery query;
}
