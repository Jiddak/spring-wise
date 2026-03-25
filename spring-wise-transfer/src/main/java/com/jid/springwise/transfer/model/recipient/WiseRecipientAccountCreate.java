package com.jid.springwise.transfer.model.recipient;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.util.Map;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseRecipientAccountCreate {
    @JsonProperty("profile")           private Long profile;
    @JsonProperty("accountHolderName") private String accountHolderName;
    @JsonProperty("currency")          private String currency;
    @JsonProperty("type")              private String type;
    @JsonProperty("details")           private Map<String, Object> details;
    @JsonProperty("ownedByCustomer")   private Boolean ownedByCustomer;
}
