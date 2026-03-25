package com.jid.springwise.balance.model.balance;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseBalanceBankDetails {
    @JsonProperty("id")       private Long id;
    @JsonProperty("currency") private String currency;
    @JsonProperty("routingNumbers") private Object routingNumbers;
    @JsonProperty("swift")    private String swift;
    @JsonProperty("iban")     private String iban;
    @JsonProperty("bankCode") private String bankCode;
    @JsonProperty("accountNumber") private String accountNumber;
    @JsonProperty("bankName") private String bankName;
    @JsonProperty("bankAddress") private Object bankAddress;
    @JsonProperty("accountHolderName") private String accountHolderName;
}
