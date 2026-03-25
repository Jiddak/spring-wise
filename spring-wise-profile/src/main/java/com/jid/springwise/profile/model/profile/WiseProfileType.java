package com.jid.springwise.profile.model.profile;
import com.fasterxml.jackson.annotation.JsonProperty;
public enum WiseProfileType {
    @JsonProperty("personal") PERSONAL,
    @JsonProperty("business") BUSINESS,
}
