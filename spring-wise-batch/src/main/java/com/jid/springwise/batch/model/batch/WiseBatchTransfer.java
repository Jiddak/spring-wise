package com.jid.springwise.batch.model.batch;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jid.springwise.core.model.WiseMoney;
import lombok.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseBatchTransfer {
    @JsonProperty("id")                    private Long id;
    @JsonProperty("status")                private String status;
    @JsonProperty("sourceCurrency")        private String sourceCurrency;
    @JsonProperty("sourceValue")           private BigDecimal sourceValue;
    @JsonProperty("targetCurrency")        private String targetCurrency;
    @JsonProperty("targetValue")           private BigDecimal targetValue;
    @JsonProperty("customerTransactionId") private String customerTransactionId;
}
