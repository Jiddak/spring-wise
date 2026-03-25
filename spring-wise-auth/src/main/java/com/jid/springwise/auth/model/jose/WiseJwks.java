package com.jid.springwise.auth.model.jose;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.util.List;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseJwks {
    @JsonProperty("keys") private List<WiseJwk> keys;
}
