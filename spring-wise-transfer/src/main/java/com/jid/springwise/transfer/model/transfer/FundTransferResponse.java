package com.jid.springwise.transfer.model.transfer;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class FundTransferResponse {
    private WiseFundTransferDetails fundTransfer;
}
