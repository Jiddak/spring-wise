package com.jid.springwise.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@Getter
@ToString(exclude = "apiToken")
public class WiseApiConfig {

    private final String baseUrl;

    private final String apiToken;

    @Builder.Default
    private final ObjectMapper objectMapper = WiseApiMapper.getMapper();

    @Builder.Default
    private final WiseApiErrorHandler errorProcessor = new DefaultWiseApiErrorHandler();

}
