package com.jid.springwise.card.model.cardtransaction;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jid.springwise.core.model.WiseMoney;
import lombok.*;
import java.time.ZonedDateTime;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseCardTransaction {
    @JsonProperty("id")            private String id;
    @JsonProperty("type")          private WiseCardTransactionType type;
    @JsonProperty("state")         private WiseCardTransactionState state;
    @JsonProperty("createdOn")     private ZonedDateTime createdOn;
    @JsonProperty("amount")        private WiseMoney amount;
    @JsonProperty("totalFees")     private WiseMoney totalFees;
    @JsonProperty("cardToken")     private String cardToken;
    @JsonProperty("merchant")      private WiseCardTransactionMerchant merchant;
    @JsonProperty("details")       private Object details;
    @JsonProperty("declineReason") private String declineReason;
}
