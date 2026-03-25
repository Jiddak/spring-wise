package com.jid.springwise.profile.model.address;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetAddressRequirementsForCountryRequest {
    private WiseAddressRequirementsFilter filter;
}
