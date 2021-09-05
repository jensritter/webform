package org.jens.webforms.core.controller.form;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Jens Ritter on 05/09/2021.
 */
class JsonFormTest {

    JsonForm form;

    @BeforeEach
    public void setUp() {
        form = new JsonForm();
    }

    @Test
    void getForm() {
    }

    @Test
    void getSchema() {
    }

    @Test
    void add() {
    }

    @Test
    void addDuplicate() {
        form.add("name", new FString("label"));
        assertThrows(IllegalArgumentException.class, () -> {
            form.add("name", new FDate("anotther"));
        });
        assertThrows(IllegalArgumentException.class, () -> {
            form.add("name", new FString("label"));
        });
    }
}
