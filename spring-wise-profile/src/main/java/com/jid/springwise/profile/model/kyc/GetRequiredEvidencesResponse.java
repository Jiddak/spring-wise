package com.jid.springwise.profile.model.kyc;
import lombok.*;
import java.util.List;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetRequiredEvidencesResponse {
    private List<WiseRequiredEvidence> evidences;
}
