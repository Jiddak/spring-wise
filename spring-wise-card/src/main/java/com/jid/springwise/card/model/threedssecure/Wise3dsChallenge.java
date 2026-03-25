package com.jid.springwise.card.model.threedssecure;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Wise3dsChallenge {
    @JsonProperty("transactionId") private String transactionId;
    @JsonProperty("cres")          private String cres;
}
