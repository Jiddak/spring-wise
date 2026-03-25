package com.jid.springwise.card.model.cardkiosk;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseCardProductionCreate {
    @JsonProperty("encryptedCardData") private String encryptedCardData;
    @JsonProperty("kioskId")           private String kioskId;
}
