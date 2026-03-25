package com.jid.springwise.auth.model.onetimetoken;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetOneTimeTokenResponse {
    private WiseOneTimeToken oneTimeToken;
}
