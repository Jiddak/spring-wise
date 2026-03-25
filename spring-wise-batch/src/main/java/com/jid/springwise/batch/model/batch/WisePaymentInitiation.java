package com.jid.springwise.batch.model.batch;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jid.springwise.core.model.WiseMoney;
import lombok.*;
import java.time.ZonedDateTime;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WisePaymentInitiation {
    @JsonProperty("id")        private Long id;
    @JsonProperty("status")    private String status;
    @JsonProperty("type")      private String type;
    @JsonProperty("amount")    private WiseMoney amount;
    @JsonProperty("createdOn") private ZonedDateTime createdOn;
}
