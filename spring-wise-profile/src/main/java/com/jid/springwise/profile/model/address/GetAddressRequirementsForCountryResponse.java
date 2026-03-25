package com.jid.springwise.profile.model.address;
import lombok.*;
import java.util.List;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetAddressRequirementsForCountryResponse {
    private List<Object> requirements;
}
