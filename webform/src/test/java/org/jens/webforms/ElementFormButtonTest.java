package org.jens.webforms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Jens Ritter on 07/09/2021.
 */
class ElementFormButtonTest {

    public static final String BUTTON_FORM = "{\"type\":\"number\",\"key\":\"name\"}";
    WebFormBuilder form;

    @BeforeEach
    public void setupElement() {
        form = new WebFormBuilder();
    }

    @SuppressWarnings("JsonStandardCompliance")
    @Test
    void testUsage() {
        ElementFormButton button = form.addButton("btnCancel", "Cancel");
        assertThat(button).isNotNull();
        assertThat(button.getTitle()).isEqualTo("Cancel");
        assertThat(button.getType()).isEqualTo("button");

        String s = form.toJson();
        assertThat(s).isEqualTo("""
            {"schema":{},"form":[{"type":"button","title":"Cancel"},{"type":"submit","title":"Submit"}]}"""
        );
    }
}
