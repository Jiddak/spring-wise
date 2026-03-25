package com.jid.springwise.balance.model.statement;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseStatementAddress {

    @JsonProperty("addressFirstLine")
    private String addressFirstLine;

    @JsonProperty("city")
    private String city;

    @JsonProperty("postCode")
    private String postCode;

    @JsonProperty("stateCode")
    private String stateCode;

    @JsonProperty("countryName")
    private String countryName;

}
