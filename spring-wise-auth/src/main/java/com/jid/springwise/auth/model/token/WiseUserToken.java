package com.jid.springwise.auth.model.token;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseUserToken {
    @JsonProperty("access_token")  private String accessToken;
    @JsonProperty("token_type")    private String tokenType;
    @JsonProperty("refresh_token") private String refreshToken;
    @JsonProperty("expires_in")    private Long expiresIn;
    @JsonProperty("scope")         private String scope;
}
