package com.jid.springwise.card.model.cardkiosk;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseCardProduction {
    @JsonProperty("cardToken")        private String cardToken;
    @JsonProperty("productionStatus") private WiseCardProductionStatus productionStatus;
    @JsonProperty("errorCode")        private String errorCode;
}
