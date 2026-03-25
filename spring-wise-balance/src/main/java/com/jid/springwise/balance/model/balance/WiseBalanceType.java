package com.jid.springwise.balance.model.balance;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum WiseBalanceType {

    @JsonProperty("STANDARD")
    STANDARD,

    @JsonProperty("SAVINGS")
    SAVINGS,

}
