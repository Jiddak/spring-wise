package com.jid.springwise.profile.model.user;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetCurrentUserResponse {
    private WiseUser user;
}
