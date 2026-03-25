package com.jid.springwise.card.model.card;
import com.fasterxml.jackson.annotation.JsonProperty;
public enum WiseCardStatus {
    @JsonProperty("M") ACTIVE,
    @JsonProperty("S") SUSPENDED,
    @JsonProperty("X") TERMINATED,
    @JsonProperty("L") LOST,
    @JsonProperty("K") STOLEN,
}
