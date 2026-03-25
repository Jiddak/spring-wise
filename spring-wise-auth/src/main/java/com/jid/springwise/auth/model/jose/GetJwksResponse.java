package com.jid.springwise.auth.model.jose;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetJwksResponse {
    private WiseJwks jwks;
}
