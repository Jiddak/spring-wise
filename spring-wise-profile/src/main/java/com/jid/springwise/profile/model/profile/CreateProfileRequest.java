package com.jid.springwise.profile.model.profile;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class CreateProfileRequest {
    private WiseProfileCreate profile;
}
