package com.jid.springwise.admin.model.directdebit;
import lombok.*;
import java.util.List;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetDirectDebitAccountsResponse {
    private List<WiseDirectDebitAccount> directDebitAccounts;
}
