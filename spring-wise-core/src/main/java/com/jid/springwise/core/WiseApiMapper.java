package com.jid.springwise.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import lombok.SneakyThrows;

public final class WiseApiMapper {

    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper()
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .findAndRegisterModules();
    }

    private WiseApiMapper() {
        throw new UnsupportedOperationException("Utility class");
    }

    @SneakyThrows
    public static String sneakyWriteAsString(Object object) {
        return mapper.writeValueAsString(object);
    }

    @SneakyThrows
    public static <T> T sneakyReadValue(String json, Class<T> clazz) {
        return mapper.readValue(json, clazz);
    }

    @SneakyThrows
    public static <T> T sneakyReadValue(String json, TypeReference<T> typeReference) {
        return mapper.readValue(json, typeReference);
    }

    public static ObjectReader getObjectReader() { return mapper.reader(); }
    public static ObjectWriter getObjectWriter() { return mapper.writer(); }
    public static ObjectMapper getMapper()       { return mapper; }
}
