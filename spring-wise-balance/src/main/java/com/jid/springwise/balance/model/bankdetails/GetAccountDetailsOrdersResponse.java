package com.jid.springwise.balance.model.bankdetails;
import lombok.*;
import java.util.List;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetAccountDetailsOrdersResponse {
    private List<WiseAccountDetailsOrder> orders;
}
