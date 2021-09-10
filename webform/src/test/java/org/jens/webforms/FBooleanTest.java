package org.jens.webforms;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Jens Ritter on 05/09/2021.
 */
class FBooleanTest extends JsonTester {

    static final String PLAIN_SCHEMA = "{\"type\":\"boolean\",\"title\":\"label\"}";

    FBoolean element;

    @BeforeEach
    public void setupElement() {
        element = new FBoolean("label");
    }

    @Override
    @Test
    public void testJson() throws JsonProcessingException {
        assertThat(toSchemaJson(element)).isEqualTo(PLAIN_SCHEMA);
        assertThat(toFormJson(element)).isEqualTo(PLAIN_FORM);

    }

    @Test
    public void setValue() throws JsonProcessingException {
        element.value(true);

        assertThat(toSchemaJson(element)).isEqualTo("{\"type\":\"boolean\",\"title\":\"label\",\"default\":true}");
        assertThat(toFormJson(element)).isEqualTo(PLAIN_FORM);

        element.value(false);

        assertThat(toSchemaJson(element)).isEqualTo("{\"type\":\"boolean\",\"title\":\"label\",\"default\":false}");
        assertThat(toFormJson(element)).isEqualTo(PLAIN_FORM);
    }

    @Test
    void setInlineTitle() throws JsonProcessingException {
        element.inlineTitle("inlineTitle");
        assertThat(toSchemaJson(element)).isEqualTo(PLAIN_SCHEMA);
        assertThat(toFormJson(element)).isEqualTo("{\"key\":\"name\",\"inlinetitle\":\"inlineTitle\"}");
    }

}
