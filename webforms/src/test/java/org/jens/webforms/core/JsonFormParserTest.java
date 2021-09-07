package org.jens.webforms.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Jens Ritter on 07/09/2021.
 */
class JsonFormParserTest {

    @BeforeEach
    public void setUpForm() {
        jsonForm = new JsonForm();
        parser = new JsonFormParser();
    }

    JsonForm jsonForm;
    JsonFormParser parser;

    @Test
    void parseElements() throws JsonProcessingException {
        jsonForm.add("welcome",
            new FString("Welcome")
                .value("value")
                .placeholder("placeholder")
                .required(true)
                .disabled(true)
                .fieldHtmlClass("fieldhtml")
                .htmlClass("htmlclass")
                .noTitle(true)
                .prepend("prepend")
                .append("append")
                .description("description")
                .readOnly(true)
        );
        Map<String, ElementSchema<?>> elements = parser.parseElements(jsonForm.toString());
        assertThat(elements).hasSize(1);
        assertThat(elements).containsOnlyKeys("welcome");

        ElementSchema<?> elementSchema = elements.get("welcome");
        assertThat(elementSchema).isNotNull();
        assertThat(elementSchema).isInstanceOf(FString.class);
        FString fstring = (FString) elementSchema;
        assertThat(fstring.getDefaultValue()).describedAs("defaultValue present").isEqualTo("value");
        assertThat(fstring.getPlaceholder()).describedAs("placeholder present").isEqualTo("placeholder");
        assertThat(fstring.isRequired()).describedAs("required is present").isEqualTo(true);
        assertThat(fstring.isDisabled()).describedAs("disabled is present").isEqualTo(true);
        assertThat(fstring.getFieldHtmlClass()).describedAs("FieldHtmlClass is present").isEqualTo("fieldhtml");
        assertThat(fstring.getHtmlClass()).describedAs("htmlClass is present").isEqualTo("htmlclass");
        assertThat(fstring.isNoTitle()).describedAs("notitle is present").isTrue();
        assertThat(fstring.getPrepend()).describedAs("prepend is present").isEqualTo("prepend");
        assertThat(fstring.getAppend()).describedAs("append is present").isEqualTo("append");
        assertThat(fstring.getDescription()).describedAs("description is present").isEqualTo("description");
        assertThat(fstring.isReadonly()).describedAs("readonly is present").isTrue();
    }

    @Test
    public void testBoolean() throws JsonProcessingException {
        jsonForm.add("bool", new FBoolean("title").inlineTitle("inlineTitle").value(true));
        Map<String, ElementSchema<?>> elements = parser.parseElements(jsonForm.toString());

        assertThat(elements).containsOnlyKeys("bool");
        ElementSchema<?> bool = elements.get("bool");
        assertThat(bool).isNotNull();

        assertThat(bool).isInstanceOf(FBoolean.class);
        FBoolean fBoolean = (FBoolean) bool;

        assertThat(fBoolean.getInlineTitle()).isEqualTo("inlineTitle");
        assertThat(fBoolean.getDefaultValue()).isEqualTo(Boolean.TRUE);
    }
}
