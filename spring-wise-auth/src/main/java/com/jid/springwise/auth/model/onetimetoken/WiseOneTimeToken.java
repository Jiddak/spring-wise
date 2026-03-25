package com.jid.springwise.auth.model.onetimetoken;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseOneTimeToken {
    @JsonProperty("oneTimeToken") private String oneTimeToken;
}
