package com.jid.springwise.transfer.model.transfer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jid.springwise.core.model.WiseMoney;
import lombok.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseTransfer {
    @JsonProperty("id")                    private Long id;
    @JsonProperty("user")                  private Long user;
    @JsonProperty("targetAccount")         private Long targetAccount;
    @JsonProperty("sourceAccount")         private Long sourceAccount;
    @JsonProperty("quote")                 private String quote;
    @JsonProperty("quoteUuid")             private String quoteUuid;
    @JsonProperty("status")                private WiseTransferStatus status;
    @JsonProperty("reference")             private String reference;
    @JsonProperty("rate")                  private BigDecimal rate;
    @JsonProperty("created")               private ZonedDateTime created;
    @JsonProperty("business")              private Long business;
    @JsonProperty("transferRequest")       private Long transferRequest;
    @JsonProperty("details")               private WiseTransferDetails details;
    @JsonProperty("hasActiveIssues")       private Boolean hasActiveIssues;
    @JsonProperty("sourceCurrency")        private String sourceCurrency;
    @JsonProperty("sourceValue")           private BigDecimal sourceValue;
    @JsonProperty("targetCurrency")        private String targetCurrency;
    @JsonProperty("targetValue")           private BigDecimal targetValue;
    @JsonProperty("customerTransactionId") private String customerTransactionId;
}
