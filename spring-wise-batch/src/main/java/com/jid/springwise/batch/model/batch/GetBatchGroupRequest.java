package com.jid.springwise.batch.model.batch;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetBatchGroupRequest {
    private long profileId;
    private String batchGroupId;
}
