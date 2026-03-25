package com.jid.springwise.admin.model.contact;
import lombok.*;
import java.util.List;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetContactsResponse {
    private List<WiseContact> contacts;
}
