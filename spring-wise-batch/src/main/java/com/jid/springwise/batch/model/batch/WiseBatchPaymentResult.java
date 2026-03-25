package com.jid.springwise.batch.model.batch;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jid.springwise.core.model.WiseMoney;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseBatchPaymentResult {
    @JsonProperty("type")   private String type;
    @JsonProperty("status") private String status;
    @JsonProperty("amount") private WiseMoney amount;
}
