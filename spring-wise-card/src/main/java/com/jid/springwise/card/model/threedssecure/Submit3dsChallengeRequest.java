package com.jid.springwise.card.model.threedssecure;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class Submit3dsChallengeRequest {
    private long profileId;
    private Wise3dsChallenge challenge;
}
