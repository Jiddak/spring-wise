package com.jid.springwise.batch.model.settlement;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class CreateBulkSettlementResponse {
    private WiseBulkSettlementResult result;
}
