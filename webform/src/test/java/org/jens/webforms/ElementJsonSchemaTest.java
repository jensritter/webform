package org.jens.webforms;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author Jens Ritter on 05/09/2021.
 */
class ElementJsonSchemaTest extends JsonTester {

    @BeforeEach
    public void setupDefaultElement() {
        element = new FString("label");
    }

    ElementSchema<String> element;

    @Override
    @Test
    public void testJson() throws JsonProcessingException {
        assertThat(toSchemaJson(element)).isEqualTo(PLAIN_SCHEMA);
        assertThat(toFormJson(element)).isEqualTo(PLAIN_FORM);
    }


    @Test
    void buildForm() {
        element = element
            .noTitle(true)
            .htmlClass("hc")
            .fieldHtmlClass("fc")
            .readOnly(true)
            .disabled(true)
            .title("title")
            .prepend("prepend")
            .append("append")
            .placeholder("placeholder");

        ElementForm mock = mock(ElementForm.class);
        element.buildDefaultForm(mock);
        element.buildForm(mock);

        verify(mock, times(1)).setNoTitle(eq(true));
        verify(mock, times(1)).setHtmlClass(eq("hc"));
        verify(mock, times(1)).setFieldHtmlClass(eq("fc"));
        verify(mock, times(1)).setPrepend(eq("prepend"));
        verify(mock, times(1)).setPlaceholder(eq("placeholder"));
        verify(mock, times(1)).setAppend(eq("append"));
        verify(mock, times(1)).setDisabled(eq(true));
        verify(mock, times(1)).setReadonly(eq(true));
    }

    @Test
    void value() throws JsonProcessingException {
        element.value("value");
        assertThat(toSchemaJson(element)).isEqualTo("{\"type\":\"string\",\"title\":\"label\",\"default\":\"value\"}");
        assertThat(toFormJson(element)).isEqualTo(PLAIN_FORM);
    }

    @Test
    void setTitle() throws JsonProcessingException {
        element.title("anotherTitle");
        assertThat(toSchemaJson(element)).isEqualTo("{\"type\":\"string\",\"title\":\"anotherTitle\"}");
        assertThat(toFormJson(element)).isEqualTo(PLAIN_FORM);
    }

    @Test
    void setRequired() throws JsonProcessingException {
        element.required(true);
        assertThat(toSchemaJson(element)).isEqualTo("{\"type\":\"string\",\"title\":\"label\",\"required\":true}");
        assertThat(toFormJson(element)).isEqualTo(PLAIN_FORM);
    }

    @Test
    void setDescription() throws JsonProcessingException {
        element.description("description");
        assertThat(toSchemaJson(element)).isEqualTo("{\"type\":\"string\",\"title\":\"label\",\"description\":\"description\"}");
        assertThat(toFormJson(element)).isEqualTo(PLAIN_FORM);
    }

    @Test
    void setNotitle() throws JsonProcessingException {
        element.noTitle(true);
        assertThat(toSchemaJson(element)).isEqualTo(PLAIN_SCHEMA);
        assertThat(toFormJson(element)).isEqualTo("{\"key\":\"name\",\"notitle\":true}");
    }

    @Test
    void setHtmlClass() throws JsonProcessingException {
        element.htmlClass("plain");
        assertThat(toSchemaJson(element)).isEqualTo(PLAIN_SCHEMA);
        assertThat(toFormJson(element)).isEqualTo("{\"htmlClass\":\"plain\",\"key\":\"name\"}");
    }

    @Test
    void setFieldHtmlClass() throws JsonProcessingException {
        element.fieldHtmlClass("plain");
        assertThat(toSchemaJson(element)).isEqualTo(PLAIN_SCHEMA);
        assertThat(toFormJson(element)).isEqualTo("{\"fieldHtmlClass\":\"plain\",\"key\":\"name\"}");
    }

    @Test
    void setPrepend() throws JsonProcessingException {
        element.prepend("prepend");
        assertThat(toSchemaJson(element)).isEqualTo(PLAIN_SCHEMA);
        assertThat(toFormJson(element)).isEqualTo("{\"prepend\":\"prepend\",\"key\":\"name\"}");
    }

    @Test
    void setPlaceholder() throws JsonProcessingException {
        element.placeholder("placeholder");
        assertThat(toSchemaJson(element)).isEqualTo(PLAIN_SCHEMA);
        assertThat(toFormJson(element)).isEqualTo("{\"placeholder\":\"placeholder\",\"key\":\"name\"}");
    }

    @Test
    void setDisabled() throws JsonProcessingException {
        element.disabled(true);
        assertThat(toSchemaJson(element)).isEqualTo(PLAIN_SCHEMA);
        assertThat(toFormJson(element)).isEqualTo("{\"disabled\":true,\"key\":\"name\"}");
    }

    @Test
    void setReadonly() throws JsonProcessingException {
        element.readOnly(true);
        assertThat(toSchemaJson(element)).isEqualTo(PLAIN_SCHEMA);
        assertThat(toFormJson(element)).isEqualTo("{\"readonly\":true,\"key\":\"name\"}");
    }

    @Test
    void setDefaultValue() {
        element.setDefaultValue("HI");
        assertThat(element.getDefaultValue()).isEqualTo("HI");

        ElementSchema<Integer> lbl = new FInteger("lbl").value(10);
        assertThat(lbl.getDefaultValue()).isEqualTo(10);
    }

}
