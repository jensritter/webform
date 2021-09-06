package org.jens.webforms.core;

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
        assertThat(toJson(element)).isEqualTo(PLAIN_SCHEMA);
        assertThat(toFormJson(element)).isEqualTo(DATE_FORM);
    }

    @Test
    public void value() throws JsonProcessingException {
        element.value(LocalDate.of(2020, 12, 31));
        assertThat(toJson(element)).isEqualTo("{\"type\":\"string\",\"title\":\"label\",\"default\":\"2020-12-31\"}");
        assertThat(toFormJson(element)).isEqualTo(DATE_FORM);
    }
}
