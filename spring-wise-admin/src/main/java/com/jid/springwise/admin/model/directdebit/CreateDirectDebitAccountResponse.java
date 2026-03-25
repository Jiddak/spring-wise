package com.jid.springwise.admin.model.directdebit;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class CreateDirectDebitAccountResponse {
    private WiseDirectDebitAccount directDebitAccount;
}
