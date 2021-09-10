package org.jens.webforms;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Jens Ritter on 05/09/2021.
 */
class FStringTest extends JsonTester {

    FString element;

    @BeforeEach
    public void setupElement() {
        element = new FString("label");
    }

    @Override
    @Test
    public void testJson() throws JsonProcessingException {

        assertThat(toSchemaJson(element)).isEqualTo(PLAIN_SCHEMA);
        assertThat(toFormJson(element)).isEqualTo(PLAIN_FORM);
    }

    @Test
    public void setValue() throws JsonProcessingException {
        element.value("wert");

        assertThat(toSchemaJson(element)).isEqualTo("{\"type\":\"string\",\"title\":\"label\",\"default\":\"wert\"}");
        assertThat(toFormJson(element)).isEqualTo(PLAIN_FORM);
    }

}
