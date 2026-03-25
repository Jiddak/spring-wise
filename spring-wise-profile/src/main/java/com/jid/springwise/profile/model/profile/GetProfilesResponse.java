package com.jid.springwise.profile.model.profile;
import lombok.*;
import java.util.List;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetProfilesResponse {
    private List<WiseProfile> profiles;
}
