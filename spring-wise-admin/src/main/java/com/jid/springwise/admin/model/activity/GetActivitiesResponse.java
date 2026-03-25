package com.jid.springwise.admin.model.activity;
import lombok.*;
import java.util.List;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GetActivitiesResponse {
    private List<WiseActivity> activities;
}
