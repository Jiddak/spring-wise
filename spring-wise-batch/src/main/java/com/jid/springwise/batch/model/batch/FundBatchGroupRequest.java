package com.jid.springwise.batch.model.batch;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class FundBatchGroupRequest {
    private long profileId;
    private String batchGroupId;
    private WiseBatchPayment payment;
}
