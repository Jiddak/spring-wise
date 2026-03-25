package com.jid.springwise.admin.model.partnercases;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetPartnerCaseRequest {
    private long profileId;
    private String caseId;
}
