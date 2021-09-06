package org.jens.webforms.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Jens Ritter on 07/09/2021.
 */
class FTextAreaTest extends JsonTester {

    public static final String FORM_TEXTAREA = "{\"type\":\"textarea\",\"key\":\"name\"}";

    FTextArea element;

    @BeforeEach
    public void setupElement() {
        element = new FTextArea("label");
    }

    @Override
    @Test
    public void testJson() throws JsonProcessingException {

        assertThat(toJson(element)).isEqualTo(PLAIN_SCHEMA);
        assertThat(toFormJson(element)).isEqualTo(FORM_TEXTAREA);
    }

    @Test
    public void setValue() throws JsonProcessingException {
        element.value("wert");

        assertThat(toJson(element)).isEqualTo("{\"type\":\"string\",\"title\":\"label\",\"default\":\"wert\"}");
        assertThat(toFormJson(element)).isEqualTo(FORM_TEXTAREA);
    }
}
