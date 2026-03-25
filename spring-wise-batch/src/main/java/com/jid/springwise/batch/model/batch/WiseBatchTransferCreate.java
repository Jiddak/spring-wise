package com.jid.springwise.batch.model.batch;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseBatchTransferCreate {
    @JsonProperty("targetAccount")         private Long targetAccount;
    @JsonProperty("quoteUuid")             private String quoteUuid;
    @JsonProperty("customerTransactionId") private String customerTransactionId;
    @JsonProperty("details")               private Object details;
}
