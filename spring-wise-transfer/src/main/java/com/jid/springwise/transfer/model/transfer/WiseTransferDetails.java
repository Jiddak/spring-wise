package com.jid.springwise.transfer.model.transfer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseTransferDetails {
    @JsonProperty("reference")        private String reference;
    @JsonProperty("transferPurpose")  private String transferPurpose;
    @JsonProperty("sourceOfFunds")    private String sourceOfFunds;
}
