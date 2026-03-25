package com.jid.springwise.admin.model.dispute;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseDisputeCreate {
    @JsonProperty("type")       private String type;
    @JsonProperty("transferId") private Long transferId;
    @JsonProperty("message")    private String message;
}
