package com.jid.springwise.batch.model.settlement;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.util.List;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseBulkSettlement {
    @JsonProperty("settlementReference") private String settlementReference;
    @JsonProperty("transferIds")         private List<Long> transferIds;
}
