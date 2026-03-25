package com.jid.springwise.auth.model.jose;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseJwk {
    @JsonProperty("kty") private String kty;
    @JsonProperty("kid") private String kid;
    @JsonProperty("use") private String use;
    @JsonProperty("alg") private String alg;
    @JsonProperty("n")   private String n;
    @JsonProperty("e")   private String e;
}
