package com.jid.springwise.balance.model.multicurrency;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jid.springwise.balance.model.balance.WiseBalance;
import lombok.*;
import java.util.List;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseMultiCurrencyAccount {
    @JsonProperty("id")       private Long id;
    @JsonProperty("profile")  private Long profile;
    @JsonProperty("balances") private List<WiseBalance> balances;
}
