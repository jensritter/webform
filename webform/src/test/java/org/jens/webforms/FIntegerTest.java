package org.jens.webforms;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Jens Ritter on 08/09/2021.
 */
public class FIntegerTest extends JsonTester {

    public static final String INTEGER_SCHEMA = "{\"type\":\"integer\",\"title\":\"label\"}";
    FInteger element;

    @BeforeEach
    public void setupElement() {
        element = new FInteger("label");
    }

    @Override
    @Test
    public void testJson() throws JsonProcessingException {
        assertThat(toSchemaJson(element)).isEqualTo(INTEGER_SCHEMA);
        assertThat(toFormJson(element)).isEqualTo(PLAIN_FORM);
        assertThat(toSchemaJson(reconvert(element))).isEqualTo(toSchemaJson(element));
        assertThat(toFormJson(reconvert(element))).isEqualTo(toFormJson(element));
    }

    @Test
    public void value() throws JsonProcessingException {
        element.value(100);
        assertThat(toSchemaJson(element)).isEqualTo("{\"type\":\"integer\",\"title\":\"label\",\"default\":100}");
        assertThat(toFormJson(element)).isEqualTo(PLAIN_FORM);
    }
}
