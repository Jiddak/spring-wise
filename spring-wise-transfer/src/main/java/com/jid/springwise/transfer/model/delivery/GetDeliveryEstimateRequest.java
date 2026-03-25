package com.jid.springwise.transfer.model.delivery;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetDeliveryEstimateRequest {
    private long profileId;
    private long transferId;
}
