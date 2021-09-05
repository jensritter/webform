package org.jens.webforms.core.controller.form;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * https://jsonform.github.io/jsonform/playground/index.html?example=schema-basic
 * https://github.com/jsonform/jsonform
 * <p>
 * TODO: https://github.com/jsonform/jsonform/wiki#multiple-options-the-checkboxes-type
 * TODO: https://github.com/jsonform/jsonform/wiki#a-list-of-radio-buttons-the-radios-type
 * TODO: https://github.com/jsonform/jsonform/wiki#group-of-buttons-the-actions-type
 *
 * @author Jens Ritter on 29/08/2021.
 */
public abstract class ElementControl {
    // https://github.com/jsonform/jsonform/wiki#common-schema-properties
    private final FormType type;

    private String title;
    private boolean required;
    private String description;

    /**
     * Mögliche Formate.
     *
     * @see <a href="https://github.com/jsonform/jsonform/wiki#schema-supported">https://github.com/jsonform/jsonform/wiki#schema-supported</a>
     */
    public enum FormType {
        FormString("string"),
        FormNumber("number"),
        FormInteger("integer"),
        FormBoolean("boolean"),
        FormArray("array"),
        FormObject("object)");

        private final String name;

        FormType(String name) {
            this.name = name;
        }

        @JsonValue
        public String toString() {
            return name;
        }
    }


    protected ElementControl(FormType type, String label) {
        this.type = type;
        this.title = label;
    }

    /**
     * Creates an ElementForm for the Control
     *
     * @param element Default-Element
     * @return modified Element
     */
    ElementForm buildForm(ElementForm element) {return element;}

    public FormType getType() {return type;}

    public String getTitle() {return title;}

    public void setTitle(String titleValue) {
        this.title = titleValue;
    }

    public boolean isRequired() {return required;}

    public ElementControl setRequired(boolean required) {
        this.required = required;
        return this;
    }

    public String getDescription() {return description;}

    public ElementControl setDescription(String description) {
        this.description = description;
        return this;
    }
}
