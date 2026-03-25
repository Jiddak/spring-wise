package com.jid.springwise.transfer.model.delivery;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetDeliveryEstimateResponse {
    private WiseDeliveryEstimate deliveryEstimate;
}
