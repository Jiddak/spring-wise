package com.jid.springwise.profile.model.kyc;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class UploadVerificationDocumentRequest {
    private long profileId;
    private WiseVerificationDocument document;
}
