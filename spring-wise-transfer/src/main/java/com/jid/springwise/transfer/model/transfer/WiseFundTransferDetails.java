package com.jid.springwise.transfer.model.transfer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseFundTransferDetails {
    @JsonProperty("type")      private String type;
    @JsonProperty("status")    private String status;
    @JsonProperty("errorCode") private String errorCode;
}
