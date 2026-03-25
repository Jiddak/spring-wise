package com.jid.springwise.profile.model.address;
import lombok.*;
import java.util.List;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetAddressesResponse {
    private List<WiseAddress> addresses;
}
