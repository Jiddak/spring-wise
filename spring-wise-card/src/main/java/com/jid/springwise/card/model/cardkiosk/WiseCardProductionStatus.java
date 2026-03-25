package com.jid.springwise.card.model.cardkiosk;
import com.fasterxml.jackson.annotation.JsonProperty;
public enum WiseCardProductionStatus {
    @JsonProperty("READY")            READY,
    @JsonProperty("IN_PROGRESS")      IN_PROGRESS,
    @JsonProperty("PRODUCED")         PRODUCED,
    @JsonProperty("PRODUCTION_ERROR") PRODUCTION_ERROR,
}
