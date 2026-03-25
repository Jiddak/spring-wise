package com.jid.springwise.admin.model.simulation;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseSimulationResult {
    @JsonProperty("id")     private Long id;
    @JsonProperty("status") private String status;
}
