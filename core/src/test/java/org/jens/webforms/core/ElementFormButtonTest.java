package org.jens.webforms.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Jens Ritter on 07/09/2021.
 */
class ElementFormButtonTest {

    public static final String BUTTON_FORM = "{\"type\":\"number\",\"key\":\"name\"}";
    JsonForm form;

    @BeforeEach
    public void setupElement() {
        form = new JsonForm();
    }

    @SuppressWarnings("JsonStandardCompliance")
    @Test
    public void testUsage() throws JsonProcessingException {
        ElementFormButton button = form.addButton("btnCancel", "Cancel");
        assertThat(button).isNotNull();
        assertThat(button.getTitle()).isEqualTo("Cancel");
        assertThat(button.getType()).isEqualTo("button");

        String s = form.toString();
        assertThat(s).isEqualTo("{\"schema\":{},\"form\":[" +
            "{\"type\":\"button\",\"title\":\"Cancel\"}," +
            "{\"type\":\"submit\",\"title\":\"Submit\"}" +
            "]}"
        );
    }

    @Test
    void submit() {
    }

    @Test
    void getType() {
    }

    @Test
    void getTitle() {
    }

    @Test
    void setTitle() {
    }

}
