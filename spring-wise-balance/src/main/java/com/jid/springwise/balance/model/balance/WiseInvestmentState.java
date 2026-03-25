package com.jid.springwise.balance.model.balance;
import com.fasterxml.jackson.annotation.JsonProperty;
public enum WiseInvestmentState {
    @JsonProperty("NOT_INVESTED") NOT_INVESTED,
    @JsonProperty("INVESTED")     INVESTED,
}
