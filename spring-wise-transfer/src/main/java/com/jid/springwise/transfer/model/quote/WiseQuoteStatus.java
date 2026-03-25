package com.jid.springwise.transfer.model.quote;
import com.fasterxml.jackson.annotation.JsonProperty;
public enum WiseQuoteStatus {
    @JsonProperty("PENDING") PENDING,
    @JsonProperty("ACCEPTED") ACCEPTED,
    @JsonProperty("FUNDED") FUNDED,
    @JsonProperty("EXPIRED") EXPIRED,
}
