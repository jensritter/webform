package org.jens.webforms.core.controller.form;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Control {
    // https://github.com/jsonform/jsonform/wiki#common-schema-properties
    private FormType type;
    private String title;
    private boolean required;
    private String description;

    @JsonIgnore
    private String displayName;

    public enum FormType {
        // https://github.com/jsonform/jsonform/wiki#schema-supported
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

    public Control() {}

    public Control(FormType type, String title) {
        this.type = type;
        this.title = title;
        this.description = RandomValues.randomDesc();
    }

    public Control(FormType type, String title, boolean required) {
        this.type = type;
        this.title = title;
        this.required = required;
        this.description = RandomValues.randomDesc();
    }

    public FormType getType() {return type;}

    public void setType(FormType type) {this.type = type;}

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public boolean isRequired() {return required;}

    public void setRequired(boolean required) {this.required = required;}

    public String getDisplayName() {return displayName;}

    public void setDisplayName(String displayName) {this.displayName = displayName;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}
}
