package com.jid.springwise.auth.model.token;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class ExchangeAuthCodeRequest {
    private String clientId;
    private String clientSecret;
    private String code;
    private String redirectUri;
}
