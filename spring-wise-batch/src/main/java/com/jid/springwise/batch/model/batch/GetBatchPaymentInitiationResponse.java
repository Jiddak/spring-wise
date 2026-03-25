package com.jid.springwise.batch.model.batch;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetBatchPaymentInitiationResponse {
    private WisePaymentInitiation paymentInitiation;
}
