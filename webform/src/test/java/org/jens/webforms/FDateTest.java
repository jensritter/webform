package org.jens.webforms;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Jens Ritter on 06/09/2021.
 */
class FDateTest extends JsonTester {

    public static final String DATE_FORM = "{\"type\":\"date\",\"key\":\"name\"}";
    FDate element;

    @BeforeEach
    public void setupElement() {
        element = new FDate("label");
    }

    @Override
    @Test
    public void testJson() throws JsonProcessingException {
        assertThat(toSchemaJson(element)).isEqualTo(PLAIN_SCHEMA);
        assertThat(toFormJson(element)).isEqualTo(DATE_FORM);
        assertThat(toSchemaJson(reconvert(element))).isEqualTo(toSchemaJson(element));
        assertThat(toFormJson(reconvert(element))).isEqualTo(toFormJson(element));
    }

    @Test
    public void value() throws JsonProcessingException {
        element.value(LocalDate.of(2020, 12, 31));
        assertThat(toSchemaJson(element)).isEqualTo("{\"type\":\"string\",\"title\":\"label\",\"default\":\"2020-12-31\"}");
        assertThat(toFormJson(element)).isEqualTo(DATE_FORM);
    }
}
