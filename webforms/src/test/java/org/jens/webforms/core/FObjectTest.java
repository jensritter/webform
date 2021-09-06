package org.jens.webforms.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Jens Ritter on 07/09/2021.
 */
class FObjectTest extends JsonTester {

    static final String PLAIN_SCHEMA = "{\"type\":\"object\",\"title\":\"label\"}";

    FObject element;

    @BeforeEach
    public void setupElement() {
        element = new FObject("label");
    }

    @Override
    @Test
    public void testJson() throws JsonProcessingException {
        assertThat(toJson(element)).isEqualTo(PLAIN_SCHEMA);
        assertThat(toFormJson(element)).isEqualTo(PLAIN_FORM);
    }

    @Test
    public void setValue() throws JsonProcessingException {
        assertThrows(IllegalStateException.class, () -> {
            element.value(Arrays.asList("1"));
        });

    }
}
