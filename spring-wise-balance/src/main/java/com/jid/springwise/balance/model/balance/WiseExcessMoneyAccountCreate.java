package com.jid.springwise.balance.model.balance;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseExcessMoneyAccountCreate {
    @JsonProperty("recipientId") private Long recipientId;
}
