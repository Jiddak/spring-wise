package com.jid.springwise.transfer.model.quote;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseQuote {
    @JsonProperty("id")                 private String id;
    @JsonProperty("sourceCurrency")     private String sourceCurrency;
    @JsonProperty("targetCurrency")     private String targetCurrency;
    @JsonProperty("sourceAmount")       private BigDecimal sourceAmount;
    @JsonProperty("targetAmount")       private BigDecimal targetAmount;
    @JsonProperty("rate")               private BigDecimal rate;
    @JsonProperty("createdTime")        private ZonedDateTime createdTime;
    @JsonProperty("expirationTime")     private ZonedDateTime expirationTime;
    @JsonProperty("rateType")           private String rateType;
    @JsonProperty("rateExpirationTime") private ZonedDateTime rateExpirationTime;
    @JsonProperty("paymentOptions")     private List<WisePaymentOption> paymentOptions;
    @JsonProperty("status")             private WiseQuoteStatus status;
    @JsonProperty("profile")            private Long profile;
    @JsonProperty("providedAmountType") private String providedAmountType;
    @JsonProperty("clientId")           private String clientId;
    @JsonProperty("payOut")             private String payOut;
}
