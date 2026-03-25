package com.jid.springwise.balance.model.statement;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseStatementRecipient {
    @JsonProperty("id")   private Long id;
    @JsonProperty("name") private String name;
}
