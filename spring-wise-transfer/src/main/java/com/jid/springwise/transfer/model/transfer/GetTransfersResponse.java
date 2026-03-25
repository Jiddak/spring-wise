package com.jid.springwise.transfer.model.transfer;
import lombok.*;
import java.util.List;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetTransfersResponse {
    private List<WiseTransfer> transfers;
}
