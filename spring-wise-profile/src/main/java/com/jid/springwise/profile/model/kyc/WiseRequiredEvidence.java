package com.jid.springwise.profile.model.kyc;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.util.List;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseRequiredEvidence {
    @JsonProperty("type")        private String type;
    @JsonProperty("description") private String description;
    @JsonProperty("options")     private List<String> options;
}
