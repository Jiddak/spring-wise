package com.jid.springwise.transfer.model.quote;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WisePrice {
    @JsonProperty("priceSetId") private Integer priceSetId;
    @JsonProperty("total")      private WisePriceValue total;
    @JsonProperty("items")      private Object items;
}
