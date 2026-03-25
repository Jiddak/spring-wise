package com.jid.springwise.profile.model.kyc;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class UploadVerificationEvidencesRequest {
    private long profileId;
    private WiseVerificationEvidences evidences;
}
