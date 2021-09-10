package org.jens.webforms;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Jens Ritter on 07/09/2021.
 */
class FNumberTest extends JsonTester {

    public static final String NUMBER_SCHEMA = "{\"type\":\"number\",\"title\":\"label\"}";
    public static final String NUMBER_FORM = "{\"type\":\"number\",\"key\":\"name\"}";
    FNumber element;

    @BeforeEach
    public void setupElement() {
        element = new FNumber("label");
    }

    @Override
    @Test
    public void testJson() throws JsonProcessingException {
        assertThat(toSchemaJson(element)).isEqualTo(NUMBER_SCHEMA);
        assertThat(toFormJson(element)).isEqualTo(PLAIN_FORM);
        assertThat(toSchemaJson(reconvert(element))).isEqualTo(toSchemaJson(element));
        assertThat(toFormJson(reconvert(element))).isEqualTo(toFormJson(element));
    }

    @Test
    public void value() throws JsonProcessingException {
        element.value(10.1D);
        assertThat(toSchemaJson(element)).isEqualTo("{\"type\":\"number\",\"title\":\"label\",\"default\":10.1}");
        assertThat(toFormJson(element)).isEqualTo(PLAIN_FORM);
    }
}
