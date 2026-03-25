package com.jid.springwise.admin.model.comparison;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseComparison {
    @JsonProperty("providers")      private List<WiseComparisonProvider> providers;
    @JsonProperty("sourceCurrency") private String sourceCurrency;
    @JsonProperty("targetCurrency") private String targetCurrency;
    @JsonProperty("sourceAmount")   private BigDecimal sourceAmount;
}
