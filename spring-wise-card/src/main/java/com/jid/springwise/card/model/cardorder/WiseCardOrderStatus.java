package com.jid.springwise.card.model.cardorder;
import com.fasterxml.jackson.annotation.JsonProperty;
public enum WiseCardOrderStatus {
    @JsonProperty("PLACED")                 PLACED,
    @JsonProperty("REQUIREMENTS_FULFILLED") REQUIREMENTS_FULFILLED,
    @JsonProperty("CARD_DETAILS_CREATED")   CARD_DETAILS_CREATED,
    @JsonProperty("PRODUCED")               PRODUCED,
    @JsonProperty("COMPLETED")              COMPLETED,
    @JsonProperty("CANCELLED")              CANCELLED,
    @JsonProperty("RETURNED")               RETURNED,
}
