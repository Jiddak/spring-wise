package com.jid.springwise.admin.model.activity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseActivityResource {
    @JsonProperty("id")   private String id;
    @JsonProperty("type") private String type;
}
