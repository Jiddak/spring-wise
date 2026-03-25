package com.jid.springwise.profile.model.address;
import lombok.*;
import java.util.List;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetAddressRequirementsResponse {
    private List<Object> requirements;
}
