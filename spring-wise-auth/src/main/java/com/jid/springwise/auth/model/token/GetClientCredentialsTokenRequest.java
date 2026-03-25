package com.jid.springwise.auth.model.token;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetClientCredentialsTokenRequest {
    private String clientId;
    private String clientSecret;
}
