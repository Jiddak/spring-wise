package com.jid.springwise.batch.model.batch;
import com.fasterxml.jackson.annotation.JsonProperty;
public enum WiseBatchGroupStatus {
    @JsonProperty("NEW")                   NEW,
    @JsonProperty("COMPLETED")             COMPLETED,
    @JsonProperty("MARKED_FOR_CANCELLATION") MARKED_FOR_CANCELLATION,
    @JsonProperty("PROCESSING")            PROCESSING,
}
