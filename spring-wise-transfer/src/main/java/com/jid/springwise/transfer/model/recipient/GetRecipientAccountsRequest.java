package com.jid.springwise.transfer.model.recipient;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetRecipientAccountsRequest {
    private long profileId;
    private String currency;
}
