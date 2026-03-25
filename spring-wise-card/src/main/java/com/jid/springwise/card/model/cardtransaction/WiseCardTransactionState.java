package com.jid.springwise.card.model.cardtransaction;
import com.fasterxml.jackson.annotation.JsonProperty;
public enum WiseCardTransactionState {
    @JsonProperty("IN_PROGRESS") IN_PROGRESS,
    @JsonProperty("COMPLETED")   COMPLETED,
    @JsonProperty("DECLINED")    DECLINED,
    @JsonProperty("CANCELLED")   CANCELLED,
    @JsonProperty("UNKNOWN")     UNKNOWN,
}
