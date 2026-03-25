package com.jid.springwise.admin.model.dispute;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetDisputeRequest {
    private long profileId;
    private String disputeId;
}
