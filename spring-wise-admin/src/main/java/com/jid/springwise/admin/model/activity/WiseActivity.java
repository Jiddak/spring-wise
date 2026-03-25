package com.jid.springwise.admin.model.activity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.time.ZonedDateTime;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseActivity {
    @JsonProperty("id")          private String id;
    @JsonProperty("type")        private String type;
    @JsonProperty("status")      private String status;
    @JsonProperty("resource")    private WiseActivityResource resource;
    @JsonProperty("createdOn")   private ZonedDateTime createdOn;
    @JsonProperty("completedOn") private ZonedDateTime completedOn;
}
