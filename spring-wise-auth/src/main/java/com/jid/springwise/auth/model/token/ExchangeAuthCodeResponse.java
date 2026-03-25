package com.jid.springwise.auth.model.token;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class ExchangeAuthCodeResponse {
    private WiseUserToken token;
}
