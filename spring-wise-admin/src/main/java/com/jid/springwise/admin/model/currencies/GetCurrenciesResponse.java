package com.jid.springwise.admin.model.currencies;
import lombok.*;
import java.util.List;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetCurrenciesResponse {
    private List<WiseCurrency> currencies;
}
