package com.jid.springwise.transfer.model.quote;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.math.BigDecimal;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseFee {
    @JsonProperty("transferwise") private BigDecimal transferwise;
    @JsonProperty("payIn")        private BigDecimal payIn;
    @JsonProperty("discount")     private BigDecimal discount;
    @JsonProperty("partner")      private BigDecimal partner;
    @JsonProperty("total")        private BigDecimal total;
}
