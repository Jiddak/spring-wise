package com.jid.springwise.admin.model.dispute;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class CreateDisputeRequest {
    private long profileId;
    private WiseDisputeCreate dispute;
}
