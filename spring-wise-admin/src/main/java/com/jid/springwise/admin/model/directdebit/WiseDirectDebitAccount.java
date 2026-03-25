package com.jid.springwise.admin.model.directdebit;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseDirectDebitAccount {
    @JsonProperty("id")            private Long id;
    @JsonProperty("profile")       private Long profile;
    @JsonProperty("alias")         private String alias;
    @JsonProperty("status")        private String status;
    @JsonProperty("mandateId")     private String mandateId;
    @JsonProperty("bankCode")      private String bankCode;
    @JsonProperty("accountNumber") private String accountNumber;
}
