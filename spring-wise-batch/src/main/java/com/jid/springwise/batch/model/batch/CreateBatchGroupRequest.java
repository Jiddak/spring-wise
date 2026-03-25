package com.jid.springwise.batch.model.batch;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class CreateBatchGroupRequest {
    private long profileId;
    private WiseBatchGroupCreate batchGroup;
}
