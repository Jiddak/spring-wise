package com.jid.springwise.batch.model.batch;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.time.ZonedDateTime;
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseBatchGroup {
    @JsonProperty("id")             private String id;
    @JsonProperty("version")        private Integer version;
    @JsonProperty("name")           private String name;
    @JsonProperty("sourceCurrency") private String sourceCurrency;
    @JsonProperty("status")         private WiseBatchGroupStatus status;
    @JsonProperty("sequenceNumber") private Long sequenceNumber;
    @JsonProperty("completedOn")    private ZonedDateTime completedOn;
    @JsonProperty("alreadyFunded")  private Boolean alreadyFunded;
}
