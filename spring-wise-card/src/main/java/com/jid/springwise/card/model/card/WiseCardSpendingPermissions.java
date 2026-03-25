package com.jid.springwise.card.model.card;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseCardSpendingPermissions {
    @JsonProperty("allowContactless")        private Boolean allowContactless;
    @JsonProperty("allowMagStripe")          private Boolean allowMagStripe;
    @JsonProperty("allowAtm")               private Boolean allowAtm;
    @JsonProperty("allowOnlineTransactions") private Boolean allowOnlineTransactions;
    @JsonProperty("allowSwipe")             private Boolean allowSwipe;
    @JsonProperty("allowChipAndPin")        private Boolean allowChipAndPin;
}
