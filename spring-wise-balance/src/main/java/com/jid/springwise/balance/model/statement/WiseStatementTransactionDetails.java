package com.jid.springwise.balance.model.statement;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseStatementTransactionDetails {
    @JsonProperty("type")              private String type;
    @JsonProperty("description")       private String description;
    @JsonProperty("amount")            private Object amount;
    @JsonProperty("sourceAmount")      private Object sourceAmount;
    @JsonProperty("targetAmount")      private Object targetAmount;
    @JsonProperty("fee")               private Object fee;
    @JsonProperty("rate")              private Object rate;
    @JsonProperty("recipient")         private WiseStatementRecipient recipient;
    @JsonProperty("sender")            private WiseStatementSender sender;
}
