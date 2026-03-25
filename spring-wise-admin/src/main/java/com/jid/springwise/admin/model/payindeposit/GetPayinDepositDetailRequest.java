package com.jid.springwise.admin.model.payindeposit;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetPayinDepositDetailRequest {
    private long profileId;
    private String paymentId;
}
