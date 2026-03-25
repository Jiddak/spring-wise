package com.jid.springwise.balance.model.bankdetails;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.util.List;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseAccountDetail {
    @JsonProperty("type")    private String type;
    @JsonProperty("fields")  private List<WiseAccountDetailField> fields;
}
