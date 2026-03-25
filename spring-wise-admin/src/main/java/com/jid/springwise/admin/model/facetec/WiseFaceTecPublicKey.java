package com.jid.springwise.admin.model.facetec;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseFaceTecPublicKey {
    @JsonProperty("publicKey") private String publicKey;
    @JsonProperty("expiresAt") private String expiresAt;
}
