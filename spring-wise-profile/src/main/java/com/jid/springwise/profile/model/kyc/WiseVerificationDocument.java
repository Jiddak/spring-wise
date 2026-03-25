package com.jid.springwise.profile.model.kyc;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseVerificationDocument {
    @JsonProperty("type")    private String type;
    @JsonProperty("country") private String country;
    @JsonProperty("side")    private String side;
    @JsonProperty("data")    private String data;
}
