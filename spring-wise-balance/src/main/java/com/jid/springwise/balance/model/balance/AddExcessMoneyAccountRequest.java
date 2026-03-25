package com.jid.springwise.balance.model.balance;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddExcessMoneyAccountRequest {

    private long profileId;

    private WiseExcessMoneyAccountCreate excessMoneyAccount;

}
