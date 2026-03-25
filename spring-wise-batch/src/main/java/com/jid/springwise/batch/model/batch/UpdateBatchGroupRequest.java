package com.jid.springwise.batch.model.batch;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class UpdateBatchGroupRequest {
    private long profileId;
    private String batchGroupId;
    private WiseBatchGroupUpdate batchGroupUpdate;
}
