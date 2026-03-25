package com.jid.springwise.admin.model.comparison;
import lombok.*;
import java.math.BigDecimal;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class CompareRequest {
    private String sourceCurrency;
    private String targetCurrency;
    private BigDecimal sourceAmount;
}
