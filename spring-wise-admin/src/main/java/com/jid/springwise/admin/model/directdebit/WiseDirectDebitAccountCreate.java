package com.jid.springwise.admin.model.directdebit;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseDirectDebitAccountCreate {
    @JsonProperty("alias")         private String alias;
    @JsonProperty("bankCode")      private String bankCode;
    @JsonProperty("accountNumber") private String accountNumber;
}
