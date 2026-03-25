package com.jid.springwise.transfer.model.transfer;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class FundTransferRequest {
    private long profileId;
    private long transferId;
    private WiseFundTransfer fundTransfer;
}
