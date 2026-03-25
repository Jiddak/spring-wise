package com.jid.springwise.profile.model.address;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseAddressDetails {
    @JsonProperty("address")     private String address;
    @JsonProperty("city")        private String city;
    @JsonProperty("postCode")    private String postCode;
    @JsonProperty("stateCode")   private String stateCode;
    @JsonProperty("countryCode") private String countryCode;
    @JsonProperty("firstLine")   private String firstLine;
}
