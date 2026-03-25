package com.jid.springwise.card.model.threedssecure;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Wise3dsChallengeResult {
    @JsonProperty("status") private String status;
    @JsonProperty("reason") private String reason;
}
