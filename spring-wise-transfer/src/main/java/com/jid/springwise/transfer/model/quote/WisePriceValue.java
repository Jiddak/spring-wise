package com.jid.springwise.transfer.model.quote;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jid.springwise.core.model.WiseMoney;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WisePriceValue {
    @JsonProperty("type")  private String type;
    @JsonProperty("label") private String label;
    @JsonProperty("value") private WiseMoney value;
}
