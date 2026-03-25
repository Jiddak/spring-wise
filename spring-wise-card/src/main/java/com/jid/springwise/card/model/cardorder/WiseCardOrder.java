package com.jid.springwise.card.model.cardorder;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseCardOrder {
    @JsonProperty("id")           private String id;
    @JsonProperty("status")       private WiseCardOrderStatus status;
    @JsonProperty("profile")      private Long profile;
    @JsonProperty("cardToken")    private String cardToken;
    @JsonProperty("cardType")     private String cardType;
    @JsonProperty("deliveryType") private String deliveryType;
    @JsonProperty("programToken") private String programToken;
}
