package com.jid.springwise.batch.model.settlement;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class CreateBulkSettlementRequest {
    private WiseBulkSettlement settlement;
}
