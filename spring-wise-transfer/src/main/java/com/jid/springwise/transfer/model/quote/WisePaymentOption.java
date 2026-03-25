package com.jid.springwise.transfer.model.quote;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WisePaymentOption {
    @JsonProperty("disabled")                   private Boolean disabled;
    @JsonProperty("estimatedDelivery")           private String estimatedDelivery;
    @JsonProperty("formattedEstimatedDelivery")  private String formattedEstimatedDelivery;
    @JsonProperty("estimatedDeliveryDelays")     private List<Object> estimatedDeliveryDelays;
    @JsonProperty("fee")                         private WiseFee fee;
    @JsonProperty("price")                       private WisePrice price;
    @JsonProperty("sourceAmount")                private BigDecimal sourceAmount;
    @JsonProperty("targetAmount")                private BigDecimal targetAmount;
    @JsonProperty("sourceCurrency")              private String sourceCurrency;
    @JsonProperty("targetCurrency")              private String targetCurrency;
    @JsonProperty("payIn")                       private String payIn;
    @JsonProperty("payOut")                      private String payOut;
    @JsonProperty("allowedProfileTypes")         private List<String> allowedProfileTypes;
    @JsonProperty("payInProduct")                private String payInProduct;
    @JsonProperty("feePercentage")               private BigDecimal feePercentage;
}
