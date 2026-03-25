package com.jid.springwise.transfer.model.rate;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseRate {
    @JsonProperty("rate")   private BigDecimal rate;
    @JsonProperty("source") private String source;
    @JsonProperty("target") private String target;
    @JsonProperty("time")   private ZonedDateTime time;
}
