package com.jid.springwise.auth.model.token;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class RefreshTokenResponse {
    private WiseUserToken token;
}
