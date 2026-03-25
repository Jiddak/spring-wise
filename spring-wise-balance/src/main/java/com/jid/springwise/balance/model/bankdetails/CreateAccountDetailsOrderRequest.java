package com.jid.springwise.balance.model.bankdetails;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class CreateAccountDetailsOrderRequest {
    private long profileId;
    private WiseAccountDetailsOrderCreate order;
}
