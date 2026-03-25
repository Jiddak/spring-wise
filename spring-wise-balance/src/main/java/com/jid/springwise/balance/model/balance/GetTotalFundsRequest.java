package com.jid.springwise.balance.model.balance;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetTotalFundsRequest {

    private long profileId;

    private String currency;

}
