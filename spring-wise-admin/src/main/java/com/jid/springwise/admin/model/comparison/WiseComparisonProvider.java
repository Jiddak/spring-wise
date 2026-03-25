package com.jid.springwise.admin.model.comparison;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseComparisonProvider {
    @JsonProperty("id")              private String id;
    @JsonProperty("name")            private String name;
    @JsonProperty("logoUrl")         private String logoUrl;
    @JsonProperty("quotes")          private List<WiseComparisonQuote> quotes;
}
