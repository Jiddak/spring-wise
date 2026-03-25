package com.jid.springwise.auth.model.token;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class RefreshTokenRequest {
    private String clientId;
    private String clientSecret;
    private String refreshToken;
}
