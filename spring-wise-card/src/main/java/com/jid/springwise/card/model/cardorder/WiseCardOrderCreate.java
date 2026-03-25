package com.jid.springwise.card.model.cardorder;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseCardOrderCreate {
    @JsonProperty("cardType")     private String cardType;
    @JsonProperty("deliveryType") private String deliveryType;
    @JsonProperty("programToken") private String programToken;
    @JsonProperty("address")      private Object address;
}
