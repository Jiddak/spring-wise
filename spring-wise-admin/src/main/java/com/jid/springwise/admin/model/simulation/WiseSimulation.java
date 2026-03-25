package com.jid.springwise.admin.model.simulation;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseSimulation {
    @JsonProperty("status")    private String status;
    @JsonProperty("errorCode") private String errorCode;
}
