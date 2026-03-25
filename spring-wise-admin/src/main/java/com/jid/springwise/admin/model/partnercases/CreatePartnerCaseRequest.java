package com.jid.springwise.admin.model.partnercases;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class CreatePartnerCaseRequest {
    private long profileId;
    private WisePartnerCaseCreate partnerCase;
}
