package com.jid.springwise.admin.model.contact;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseContact {
    @JsonProperty("id")      private Long id;
    @JsonProperty("name")    private String name;
    @JsonProperty("email")   private String email;
    @JsonProperty("profile") private Long profile;
}
