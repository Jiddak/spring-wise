package com.jid.springwise.balance.model.statement;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseStatementIssuer {
    @JsonProperty("name")    private String name;
    @JsonProperty("firstLine")  private String firstLine;
    @JsonProperty("city")    private String city;
    @JsonProperty("postCode") private String postCode;
    @JsonProperty("stateCode") private String stateCode;
    @JsonProperty("country")  private String country;
}
