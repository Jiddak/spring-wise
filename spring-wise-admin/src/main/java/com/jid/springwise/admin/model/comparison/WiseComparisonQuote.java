package com.jid.springwise.admin.model.comparison;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.math.BigDecimal;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseComparisonQuote {
    @JsonProperty("fee")            private BigDecimal fee;
    @JsonProperty("rate")           private BigDecimal rate;
    @JsonProperty("toAmount")       private BigDecimal toAmount;
    @JsonProperty("fromAmount")     private BigDecimal fromAmount;
    @JsonProperty("sourceCurrency") private String sourceCurrency;
    @JsonProperty("targetCurrency") private String targetCurrency;
    @JsonProperty("targetCountry")  private String targetCountry;
    @JsonProperty("speed")          private Object speed;
}
