package com.jid.springwise.balance.model.bankdetails;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.util.List;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseBankAccountDetails {
    @JsonProperty("id")             private Long id;
    @JsonProperty("currency")       private String currency;
    @JsonProperty("bankFeatures")   private WiseBankFeatures bankFeatures;
    @JsonProperty("payInDetails")   private WisePayInDetails payInDetails;
    @JsonProperty("accountDetails") private List<WiseAccountDetail> accountDetails;
}
