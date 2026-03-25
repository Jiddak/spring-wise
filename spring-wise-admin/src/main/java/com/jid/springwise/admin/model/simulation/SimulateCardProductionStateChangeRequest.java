package com.jid.springwise.admin.model.simulation;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class SimulateCardProductionStateChangeRequest {
    private long profileId;
    private String cardToken;
    private WiseSimulation simulation;
}
