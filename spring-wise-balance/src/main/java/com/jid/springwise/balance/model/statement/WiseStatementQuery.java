package com.jid.springwise.balance.model.statement;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseStatementQuery {
    @JsonProperty("intervalStart") private String intervalStart;
    @JsonProperty("intervalEnd")   private String intervalEnd;
    @JsonProperty("currency")      private String currency;
    @JsonProperty("accountId")     private Long accountId;
}
