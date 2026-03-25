package com.jid.springwise.batch.model.batch;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseBatchGroupUpdate {
    @JsonProperty("version") private Integer version;
    @JsonProperty("status")  private WiseBatchGroupStatus status;
}
