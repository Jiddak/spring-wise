package com.jid.springwise.admin.model.partnercases;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WisePartnerCaseCreate {
    @JsonProperty("type")        private String type;
    @JsonProperty("transferId")  private Long transferId;
    @JsonProperty("description") private String description;
}
