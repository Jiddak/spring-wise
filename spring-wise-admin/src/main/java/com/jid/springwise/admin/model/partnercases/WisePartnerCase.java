package com.jid.springwise.admin.model.partnercases;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.time.ZonedDateTime;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WisePartnerCase {
    @JsonProperty("id")          private String id;
    @JsonProperty("status")      private String status;
    @JsonProperty("type")        private String type;
    @JsonProperty("createdOn")   private ZonedDateTime createdOn;
    @JsonProperty("transferId")  private Long transferId;
    @JsonProperty("description") private String description;
}
