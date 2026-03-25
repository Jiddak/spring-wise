package com.jid.springwise.transfer.model.recipient;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.time.ZonedDateTime;
import java.util.Map;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseRecipientAccount {
    @JsonProperty("id")                private Long id;
    @JsonProperty("profile")           private Long profile;
    @JsonProperty("accountHolderName") private String accountHolderName;
    @JsonProperty("currency")          private String currency;
    @JsonProperty("country")           private String country;
    @JsonProperty("type")              private String type;
    @JsonProperty("details")           private Map<String, Object> details;
    @JsonProperty("user")              private Long user;
    @JsonProperty("active")            private Boolean active;
    @JsonProperty("ownedByCustomer")   private Boolean ownedByCustomer;
    @JsonProperty("createdAt")         private ZonedDateTime createdAt;
}
