package com.jid.springwise.auth.model.sca;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseScaChallenge {
    @JsonProperty("id")     private String id;
    @JsonProperty("status") private String status;
    @JsonProperty("type")   private String type;
}
