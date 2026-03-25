package com.jid.springwise.profile.model.profile;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseProfileCreate {
    @JsonProperty("type")    private WiseProfileType type;
    @JsonProperty("details") private WiseProfileDetails details;
}
