package com.jid.springwise.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseMoney {

    @JsonProperty("value")
    private BigDecimal value;

    @JsonProperty("currency")
    private String currency;

}
