package com.jid.springwise.admin.model.contact;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseContactCreate {
    @JsonProperty("name")  private String name;
    @JsonProperty("email") private String email;
}
