package com.jid.springwise.batch.model.batch;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class AddTransferToBatchGroupRequest {
    private long profileId;
    private String batchGroupId;
    private WiseBatchTransferCreate transfer;
}
