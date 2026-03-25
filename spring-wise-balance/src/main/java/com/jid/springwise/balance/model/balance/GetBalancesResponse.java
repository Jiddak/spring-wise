package com.jid.springwise.balance.model.balance;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetBalancesResponse {

    private List<WiseBalance> balances;

}
