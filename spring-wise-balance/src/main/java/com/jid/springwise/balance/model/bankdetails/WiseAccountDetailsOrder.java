package com.jid.springwise.balance.model.bankdetails;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.time.ZonedDateTime;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseAccountDetailsOrder {
    @JsonProperty("id")        private Long id;
    @JsonProperty("profile")   private Long profile;
    @JsonProperty("currency")  private String currency;
    @JsonProperty("status")    private String status;
    @JsonProperty("createdAt") private ZonedDateTime createdAt;
}
