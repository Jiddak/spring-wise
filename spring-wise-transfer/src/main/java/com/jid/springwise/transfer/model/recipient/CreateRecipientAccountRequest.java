package com.jid.springwise.transfer.model.recipient;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class CreateRecipientAccountRequest {
    private WiseRecipientAccountCreate recipientAccount;
}
