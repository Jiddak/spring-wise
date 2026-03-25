package com.jid.springwise.admin.model.payindeposit;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jid.springwise.core.model.WiseMoney;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WisePayinDepositDetail {
    @JsonProperty("id")        private String id;
    @JsonProperty("amount")    private WiseMoney amount;
    @JsonProperty("reference") private String reference;
    @JsonProperty("status")    private String status;
}
