/**
 *
 */
package com.jmgits.sample.sbhcp.common.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmgits.sample.sbhcp.common.exception.JsonException;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.MapperFeature.USE_ANNOTATIONS;

/**
 * Created by javi.more.garc on 08/01/17.
 */
public final class JsonUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.setSerializationInclusion(NON_NULL);
        MAPPER.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
        MAPPER.configure(USE_ANNOTATIONS, false);
    }

    private JsonUtils() {
    }

    public static <T> T read(String value, Class<T> clazz) {
        try {
            return MAPPER.readValue(value, clazz);
        } catch (Exception e) {
            throw new JsonException(String.format("Could not read value '%s' as '%s'", value, clazz.getSimpleName()), e);
        }
    }

    public static <T> T read(String value, TypeReference<T> typeReference) {
        try {
            return MAPPER.readValue(value, typeReference);
        } catch (Exception e) {
            throw new JsonException(String.format("Could not read value '%s' as '%s'", value, typeReference), e);
        }
    }

    public static String write(Object value) {
        try {
            return MAPPER.writeValueAsString(value);
        } catch (Exception e) {
            throw new JsonException(String.format("Could not write value '%s'", value.toString()), e);
        }
    }
}
