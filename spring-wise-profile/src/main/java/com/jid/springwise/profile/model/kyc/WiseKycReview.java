package com.jid.springwise.profile.model.kyc;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseKycReview {
    @JsonProperty("state")   private String state;
    @JsonProperty("message") private String message;
}
