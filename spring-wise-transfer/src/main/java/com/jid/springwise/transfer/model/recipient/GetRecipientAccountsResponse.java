package com.jid.springwise.transfer.model.recipient;
import lombok.*;
import java.util.List;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetRecipientAccountsResponse {
    private List<WiseRecipientAccount> recipientAccounts;
}
