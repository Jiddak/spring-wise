package com.jid.springwise.balance.model.statement;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseStatementAccountHolder {
    @JsonProperty("type")      private String type;
    @JsonProperty("address")   private WiseStatementAddress address;
    @JsonProperty("firstName") private String firstName;
    @JsonProperty("lastName")  private String lastName;
}
