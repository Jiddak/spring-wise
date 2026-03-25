package com.jid.springwise.transfer.model.transfer;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetTransfersRequest {
    private long profileId;
}
