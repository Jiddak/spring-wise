package com.jid.springwise.admin.model.simulation;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class SimulateTransferStateChangeRequest {
    private long transferId;
    private WiseSimulation simulation;
}
