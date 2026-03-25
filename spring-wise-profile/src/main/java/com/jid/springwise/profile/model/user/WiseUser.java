package com.jid.springwise.profile.model.user;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseUser {
    @JsonProperty("id")               private Long id;
    @JsonProperty("name")             private String name;
    @JsonProperty("email")            private String email;
    @JsonProperty("active")           private Boolean active;
    @JsonProperty("details")          private Object details;
    @JsonProperty("registrationTime") private String registrationTime;
}
