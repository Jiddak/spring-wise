package com.jid.springwise.auth.model.sca;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetScaChallengeResponse {
    private WiseScaChallenge challenge;
}
