package com.jid.springwise.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiseClientApiError {

    @JsonProperty("timestamp")
    private String timestamp;

    @JsonProperty("code")
    private String code;

    @JsonProperty("path")
    private String path;

    @JsonProperty("message")
    private String message;

    @JsonProperty("arguments")
    private List<Object> arguments;

    @JsonProperty("error")
    private String error;

    @JsonProperty("error_description")
    private String errorDescription;

    @JsonProperty("status")
    private String status;

}
