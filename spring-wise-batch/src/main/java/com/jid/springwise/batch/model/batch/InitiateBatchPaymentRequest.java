package com.jid.springwise.batch.model.batch;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class InitiateBatchPaymentRequest {
    private long profileId;
    private String batchGroupId;
    private WisePaymentInitiationCreate paymentInitiation;
}
