package org.jens.webforms.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

/**
 * Basis-Pojo für alle Elemente.
 * <p>
 * Nur eine teilmenge von Properties werden direkt nach Json gemappt. Viele Properties werden via JsonForm zu einem ElementForm umgewandelt
 * <p>
 * https://jsonform.github.io/jsonform/playground/index.html?example=schema-basic
 * https://github.com/jsonform/jsonform
 * <p>
 * TODO: https://github.com/jsonform/jsonform/wiki#multiple-options-the-checkboxes-type
 * TODO: https://github.com/jsonform/jsonform/wiki#a-list-of-radio-buttons-the-radios-type
 * TODO: https://github.com/jsonform/jsonform/wiki#group-of-buttons-the-actions-type
 *
 * @author Jens Ritter on 29/08/2021.
 * @see JsonForm
 */
@SuppressWarnings("NegativelyNamedBooleanVariable")
@JsonInclude(Include.NON_NULL)
public abstract class ElementSchema<T> {

    private final FormType type;

    // https://github.com/jsonform/jsonform/wiki#common-schema-properties
    private String title;
    @Nullable
    private Boolean required;
    @Nullable
    private String description;
    @Nullable
    private String defaultValue;

    /* form-properties */
    private boolean notitle;
    private String htmlClass;
    private String fieldHtmlClass;
    private String prepend;
    private String placeholder;
    private boolean disabled;
    private boolean readonly;

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


    protected ElementSchema(FormType type, String label) {
        this.type = type;
        this.title = label;
    }

    /**
     * Creates an ElementForm for the Control
     *
     * @param element Default-Element
     * @return modified Element
     */
    ElementForm buildForm(ElementForm element) {
        element.setNoTitle(notitle);
        element.setHtmlClass(htmlClass);
        element.setFieldHtmlClass(fieldHtmlClass);

        element.setPrepend(prepend);
        element.setPlaceholder(placeholder);
        element.setDisabled(disabled);
        element.setReadonly(readonly);

        return element;
    }

    public abstract ElementSchema<T> value(@Nullable T value);


    public FormType getType() {return type;}

    public String getTitle() {return title;}

    public void setTitle(String titleValue) {
        this.title = titleValue;
    }

    @Nullable
    public Boolean getRequired() {return this.required;}

    public boolean isRequired() {return required != null;}

    public ElementSchema<T> required(boolean req) {
        this.required = req ? Boolean.TRUE : null;
        return this;
    }

    public @Nullable String getDescription() {return description;}

    public ElementSchema<T> description(String desc) {
        this.description = desc;
        return this;
    }

    @JsonIgnore
    public boolean isNotitle() {return notitle;}

    public ElementSchema<T> setNotitle(boolean notitle) {
        this.notitle = notitle;
        return this;
    }

    @JsonIgnore
    public String getHtmlClass() {return htmlClass;}

    public ElementSchema<T> setHtmlClass(String htmlClass) {
        this.htmlClass = htmlClass;
        return this;
    }

    @JsonIgnore
    public String getFieldHtmlClass() {return fieldHtmlClass;}

    public ElementSchema<T> setFieldHtmlClass(String fieldHtmlClass) {
        this.fieldHtmlClass = fieldHtmlClass;
        return this;
    }

    @JsonIgnore
    public String getPrepend() {return prepend;}

    public ElementSchema<T> setPrepend(String prepend) {
        this.prepend = prepend;
        return this;
    }

    @JsonIgnore
    public String getPlaceholder() {return placeholder;}

    public ElementSchema<T> placeholder(String plholder) {
        this.placeholder = plholder;
        return this;
    }

    @JsonIgnore
    public boolean isDisabled() {return disabled;}

    public ElementSchema<T> setDisabled(boolean disabled) {
        this.disabled = disabled;
        return this;
    }

    @JsonIgnore
    public boolean isReadonly() {return readonly;}

    public ElementSchema<T> setReadonly(boolean readonly) {
        this.readonly = readonly;
        return this;
    }

    @JsonProperty("default")
    public @Nullable String getDefaultValue() {return defaultValue;}

    ElementSchema<T> setDefaultValue(Optional<T> opt) {
        this.defaultValue = opt.map(Object::toString).orElse(null);
        return this;
    }
}
