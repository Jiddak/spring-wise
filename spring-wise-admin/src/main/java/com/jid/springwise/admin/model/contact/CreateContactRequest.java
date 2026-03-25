package com.jid.springwise.admin.model.contact;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class CreateContactRequest {
    private WiseContactCreate contact;
}
