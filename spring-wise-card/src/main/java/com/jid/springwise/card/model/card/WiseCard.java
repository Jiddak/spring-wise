package com.jid.springwise.card.model.card;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseCard {
    @JsonProperty("token")          private String token;
    @JsonProperty("status")         private WiseCardStatus status;
    @JsonProperty("createdOn")      private String createdOn;
    @JsonProperty("last4Digits")    private String last4Digits;
    @JsonProperty("cardHolderName") private String cardHolderName;
    @JsonProperty("expiryMonth")    private String expiryMonth;
    @JsonProperty("expiryYear")     private String expiryYear;
    @JsonProperty("walletId")       private String walletId;
    @JsonProperty("programToken")   private String programToken;
    @JsonProperty("cardType")       private String cardType;
    @JsonProperty("deliveryType")   private String deliveryType;
    @JsonProperty("brand")          private String brand;
}
