package com.jid.springwise.balance.model.bankdetails;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class CreateBankDetailsRequest {
    private long profileId;
    private WiseBankDetailsCreate bankDetails;
}
