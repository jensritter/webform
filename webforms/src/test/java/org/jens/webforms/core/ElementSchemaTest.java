package org.jens.webforms.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * @author Jens Ritter on 05/09/2021.
 */
class ElementSchemaTest extends JsonTester {

    @BeforeEach
    public void setupDefaultElement() {
        element = new FString("label");
    }

    ElementSchema<String> element;

    @Override
    @Test
    public void testJson() throws JsonProcessingException {
        assertThat(toJson(element)).isEqualTo(PLAIN_SCHEMA);
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
        ElementForm elementForm = element.buildForm(mock);

        assertThat(mock).isEqualTo(elementForm);

        verify(mock).setNoTitle(eq(true));
        verify(mock).setHtmlClass(eq("hc"));
        verify(mock).setFieldHtmlClass(eq("fc"));
        verify(mock).setPrepend(eq("prepend"));
        verify(mock).setPlaceholder(eq("placeholder"));
        verify(mock).setAppend(eq("append"));
        verify(mock).setDisabled(eq(true));
        verify(mock).setReadonly(eq(true));
    }

    @Test
    void value() throws JsonProcessingException {
        element.value("value");
        assertThat(toJson(element)).isEqualTo("{\"type\":\"string\",\"title\":\"label\",\"default\":\"value\"}");
        assertThat(toFormJson(element)).isEqualTo(PLAIN_FORM);
    }

    @Test
    void setTitle() throws JsonProcessingException {
        element.title("anotherTitle");
        assertThat(toJson(element)).isEqualTo("{\"type\":\"string\",\"title\":\"anotherTitle\"}");
        assertThat(toFormJson(element)).isEqualTo(PLAIN_FORM);
    }

    @Test
    void setRequired() throws JsonProcessingException {
        element.required(true);
        assertThat(toJson(element)).isEqualTo("{\"type\":\"string\",\"title\":\"label\",\"required\":true}");
        assertThat(toFormJson(element)).isEqualTo(PLAIN_FORM);
    }

    @Test
    void setDescription() throws JsonProcessingException {
        element.description("description");
        assertThat(toJson(element)).isEqualTo("{\"type\":\"string\",\"title\":\"label\",\"description\":\"description\"}");
        assertThat(toFormJson(element)).isEqualTo(PLAIN_FORM);
    }

    @Test
    void setNotitle() throws JsonProcessingException {
        element.noTitle(true);
        assertThat(toJson(element)).isEqualTo(PLAIN_SCHEMA);
        assertThat(toFormJson(element)).isEqualTo("{\"key\":\"name\",\"notitle\":true}");
    }

    @Test
    void setHtmlClass() throws JsonProcessingException {
        element.htmlClass("plain");
        assertThat(toJson(element)).isEqualTo(PLAIN_SCHEMA);
        assertThat(toFormJson(element)).isEqualTo("{\"key\":\"name\",\"htmlClass\":\"plain\"}");
    }

    @Test
    void setFieldHtmlClass() throws JsonProcessingException {
        element.fieldHtmlClass("plain");
        assertThat(toJson(element)).isEqualTo(PLAIN_SCHEMA);
        assertThat(toFormJson(element)).isEqualTo("{\"key\":\"name\",\"fieldHtmlClass\":\"plain\"}");
    }

    @Test
    void setPrepend() throws JsonProcessingException {
        element.prepend("prepend");
        assertThat(toJson(element)).isEqualTo(PLAIN_SCHEMA);
        assertThat(toFormJson(element)).isEqualTo("{\"key\":\"name\",\"prepend\":\"prepend\"}");
    }

    @Test
    void setPlaceholder() throws JsonProcessingException {
        element.placeholder("placeholder");
        assertThat(toJson(element)).isEqualTo(PLAIN_SCHEMA);
        assertThat(toFormJson(element)).isEqualTo("{\"key\":\"name\",\"placeholder\":\"placeholder\"}");
    }

    @Test
    void setDisabled() throws JsonProcessingException {
        element.disabled(true);
        assertThat(toJson(element)).isEqualTo(PLAIN_SCHEMA);
        assertThat(toFormJson(element)).isEqualTo("{\"key\":\"name\",\"disabled\":true}");
    }

    @Test
    void setReadonly() throws JsonProcessingException {
        element.readOnly(true);
        assertThat(toJson(element)).isEqualTo(PLAIN_SCHEMA);
        assertThat(toFormJson(element)).isEqualTo("{\"key\":\"name\",\"readonly\":true}");
    }

    @Test
    void setDefaultValue() {
        element.setDefaultValue("HI");
        assertThat(element.getDefaultValue()).isEqualTo("HI");

        ElementSchema<Integer> lbl = new FInteger("lbl").value(10);
        assertThat(lbl.getDefaultValue()).isEqualTo(10);
    }

}