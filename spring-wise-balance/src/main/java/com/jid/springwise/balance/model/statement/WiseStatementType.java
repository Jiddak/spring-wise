package com.jid.springwise.balance.model.statement;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum WiseStatementType {

    @JsonProperty("COMPACT")
    COMPACT,

    @JsonProperty("FLAT")
    FLAT,

}
