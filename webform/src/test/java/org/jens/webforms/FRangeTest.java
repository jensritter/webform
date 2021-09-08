package org.jens.webforms;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Jens Ritter on 07/09/2021.
 */
class FRangeTest extends JsonTester {
    static final String RANGE_SCHEMA = "{\"type\":\"integer\",\"title\":\"label\"}";
    public static final String RANGE_FORM = "{\"type\":\"range\",\"key\":\"name\"}";

    FRange element;

    @BeforeEach
    public void setupElement() {
        element = new FRange("label");
    }

    @Override
    @Test
    public void testJson() throws JsonProcessingException {
        assertThat(toJson(element)).isEqualTo(RANGE_SCHEMA);
        assertThat(toFormJson(element)).isEqualTo(RANGE_FORM);
    }

    @Test
    public void setValue() throws JsonProcessingException {
        element.value(10);
        assertThat(toJson(element)).isEqualTo("{\"type\":\"integer\",\"title\":\"label\",\"default\":10}");
        assertThat(toFormJson(element)).isEqualTo(RANGE_FORM);
    }

    @Test
    public void setMinMax() throws JsonProcessingException {
        element.minimum(10).maximum(20);

        assertThat(toJson(element)).isEqualTo("{\"type\":\"integer\",\"title\":\"label\",\"minimum\":10,\"maximum\":20}");
        assertThat(toFormJson(element)).isEqualTo(RANGE_FORM);

        assertThat(element.getMinimum()).isEqualTo(10);
        assertThat(element.getMaximum()).isEqualTo(20);
    }

    @Test
    public void setFormStep() throws JsonProcessingException {
        element.formStep(10);
        assertThat(toJson(element)).isEqualTo(RANGE_SCHEMA);
        assertThat(toFormJson(element)).isEqualTo("{\"type\":\"range\",\"key\":\"name\",\"step\":10}");

    }

    @Test
    public void setFormIndicator() throws JsonProcessingException {
        element.formIndicator(true);
        assertThat(toJson(element)).isEqualTo(RANGE_SCHEMA);
        assertThat(toFormJson(element)).isEqualTo("{\"type\":\"range\",\"key\":\"name\",\"indicator\":true}");

    }


}
