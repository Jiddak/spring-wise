package com.jid.springwise.transfer.model.transfer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseTransferCreate {
    @JsonProperty("targetAccount")         private Long targetAccount;
    @JsonProperty("quoteUuid")             private String quoteUuid;
    @JsonProperty("customerTransactionId") private String customerTransactionId;
    @JsonProperty("details")               private WiseTransferDetails details;
}
