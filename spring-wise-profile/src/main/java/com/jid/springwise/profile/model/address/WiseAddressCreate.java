package com.jid.springwise.profile.model.address;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseAddressCreate {
    @JsonProperty("profile") private Long profile;
    @JsonProperty("details") private WiseAddressDetails details;
}
