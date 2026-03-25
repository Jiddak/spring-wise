package com.jid.springwise.auth.model.sca;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class InitiateScaChallengeRequest {
    private long profileId;
    private WiseScaChallengeCreate challenge;
}
