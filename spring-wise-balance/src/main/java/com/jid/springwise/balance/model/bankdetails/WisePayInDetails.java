package com.jid.springwise.balance.model.bankdetails;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WisePayInDetails {
    @JsonProperty("reference")  private String reference;
    @JsonProperty("bankName")   private String bankName;
    @JsonProperty("accountNumber") private String accountNumber;
}
