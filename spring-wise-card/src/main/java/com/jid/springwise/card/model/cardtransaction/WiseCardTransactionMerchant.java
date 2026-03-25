package com.jid.springwise.card.model.cardtransaction;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseCardTransactionMerchant {
    @JsonProperty("name")          private String name;
    @JsonProperty("category")      private String category;
    @JsonProperty("categoryCode")  private String categoryCode;
    @JsonProperty("city")          private String city;
    @JsonProperty("country")       private String country;
}
