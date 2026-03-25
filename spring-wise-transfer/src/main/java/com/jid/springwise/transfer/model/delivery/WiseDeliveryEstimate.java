package com.jid.springwise.transfer.model.delivery;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseDeliveryEstimate {
    @JsonProperty("estimatedDeliveryDate") private String estimatedDeliveryDate;
}
