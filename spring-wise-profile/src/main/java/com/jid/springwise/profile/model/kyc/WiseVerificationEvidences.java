package com.jid.springwise.profile.model.kyc;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.util.List;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseVerificationEvidences {
    @JsonProperty("evidences") private List<Object> evidences;
}
