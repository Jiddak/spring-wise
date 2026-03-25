package com.jid.springwise.profile.model.user;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseUserSecurity {
    @JsonProperty("twoFactorAuth") private Boolean twoFactorAuth;
    @JsonProperty("phoneNumber")   private String phoneNumber;
}
