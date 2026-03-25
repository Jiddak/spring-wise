package com.jid.springwise.auth.model.sca;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetScaChallengeRequest {
    private long profileId;
    private String challengeId;
}
