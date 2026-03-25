package com.jid.springwise.auth.model.token;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetClientCredentialsTokenResponse {
    private WiseUserToken token;
}
