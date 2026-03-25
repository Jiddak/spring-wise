package com.jid.springwise.admin.model.directdebit;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class CreateDirectDebitAccountRequest {
    private long profileId;
    private WiseDirectDebitAccountCreate directDebitAccount;
}
