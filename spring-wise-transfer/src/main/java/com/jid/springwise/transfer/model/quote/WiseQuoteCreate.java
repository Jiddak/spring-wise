package com.jid.springwise.transfer.model.quote;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.math.BigDecimal;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseQuoteCreate {
    @JsonProperty("sourceCurrency") private String sourceCurrency;
    @JsonProperty("targetCurrency") private String targetCurrency;
    @JsonProperty("sourceAmount")   private BigDecimal sourceAmount;
    @JsonProperty("targetAmount")   private BigDecimal targetAmount;
    @JsonProperty("targetAccount")  private Long targetAccount;
    @JsonProperty("payOut")         private String payOut;
    @JsonProperty("preferredPayIn") private String preferredPayIn;
}
