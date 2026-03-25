package com.jid.springwise.transfer.model.transfer;
import com.fasterxml.jackson.annotation.JsonProperty;
public enum WiseTransferStatus {
    @JsonProperty("incoming_payment_waiting")   INCOMING_PAYMENT_WAITING,
    @JsonProperty("incoming_payment_initiated") INCOMING_PAYMENT_INITIATED,
    @JsonProperty("processing")                 PROCESSING,
    @JsonProperty("funds_converted")            FUNDS_CONVERTED,
    @JsonProperty("outgoing_payment_sent")      OUTGOING_PAYMENT_SENT,
    @JsonProperty("cancelled")                  CANCELLED,
    @JsonProperty("funds_refunded")             FUNDS_REFUNDED,
    @JsonProperty("bounced_back")               BOUNCED_BACK,
    @JsonProperty("charged_back")               CHARGED_BACK,
    @JsonProperty("unknown")                    UNKNOWN,
}
