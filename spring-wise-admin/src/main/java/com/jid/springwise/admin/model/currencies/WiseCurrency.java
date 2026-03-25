package com.jid.springwise.admin.model.currencies;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseCurrency {
    @JsonProperty("code")          private String code;
    @JsonProperty("symbol")        private String symbol;
    @JsonProperty("name")          private String name;
    @JsonProperty("decimalDigits") private Integer decimalDigits;
}
