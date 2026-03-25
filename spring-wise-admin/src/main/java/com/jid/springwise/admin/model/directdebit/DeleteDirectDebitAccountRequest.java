package com.jid.springwise.admin.model.directdebit;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class DeleteDirectDebitAccountRequest {
    private long profileId;
    private long directDebitAccountId;
}
