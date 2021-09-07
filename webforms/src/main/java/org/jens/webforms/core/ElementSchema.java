package org.jens.webforms.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
@SuppressWarnings({"NegativelyNamedBooleanVariable", "WeakerAccess"})
@JsonInclude(Include.NON_DEFAULT)
public abstract class ElementSchema<T> implements ElementParseable {
    private final Logger logger = LoggerFactory.getLogger(ElementSchema.class);

    private final FormType type;

    // https://github.com/jsonform/jsonform/wiki#common-schema-properties
    private String title;
    private boolean required;

    @Nullable private String description;
    @Nullable private Object defaultValue;

    /* form-properties */
    private boolean notitle;
    private boolean disabled;
    private boolean readonly;
    @Nullable private String htmlClass;
    @Nullable private String fieldHtmlClass;
    @Nullable private String prepend;
    @Nullable private String append;
    @Nullable private String placeholder;


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
        FormObject("object");

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
     */
    void buildForm(ElementForm element) {
        element.setNoTitle(notitle);
        element.setHtmlClass(htmlClass);
        element.setFieldHtmlClass(fieldHtmlClass);

        element.setPrepend(prepend);
        element.setAppend(append);
        element.setPlaceholder(placeholder);
        element.setDisabled(disabled);
        element.setReadonly(readonly);
    }

    public abstract ElementSchema<T> value(@Nullable T value);

    void applyDefaultFromJson(JsonNode schemaElement, JsonNode formElement) {
        String schemaTitle = getValueAsString(schemaElement, "title");
        if(schemaTitle == null) {
            logger.warn("Element does not have a title {}", schemaElement);
            setTitle("");
        } else {
            setTitle(schemaTitle);
        }

        setRequired(getValueAsBoolean(schemaElement, "required"));
        setDescription(getValueAsString(schemaElement, "description"));
//        setDefaultValue(getValueAsString(schemaElement, "default"));
        setNotitle(getValueAsBoolean(formElement, "notitle"));
        setDisabled(getValueAsBoolean(formElement, "disabled"));
        setReadonly(getValueAsBoolean(formElement, "readonly"));
        setFieldHtmlClass(getValueAsString(formElement, "fieldHtmlClass"));
        setHtmlClass(getValueAsString(formElement, "htmlClass"));
        setPrepend(getValueAsString(formElement, "prepend"));
        setAppend(getValueAsString(formElement, "append"));
        setPlaceholder(getValueAsString(formElement, "placeholder"));
    }

    abstract void parseForm(JsonNode schemaElement, JsonNode formElement, Optional<JsonNode> defaultValueNode);

    boolean getValueAsBoolean(JsonNode schemaElement, String key) {
        JsonNode entry = schemaElement.get(key);
        return entry != null && entry.asBoolean();
    }

    @Nullable String getValueAsString(JsonNode node, String key) {
        JsonNode jsonNode = node.get(key);
        if(jsonNode != null) {
            return jsonNode.asText();
        }
        return null;
    }


    //
    // bean-methods
    //

    public FormType getType() {return type;}

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public void setRequired(boolean required) {this.required = required;}

    public boolean isRequired() {return this.required;}

    public @Nullable String getDescription() {return description;}

    public void setDescription(@Nullable String description) {this.description = description;}

    @JsonProperty("default")
    public @Nullable Object getDefaultValue() {return defaultValue;}

    ElementSchema<T> setDefaultValue(@Nullable Object opt) {
        this.defaultValue = opt;
        return this;
    }

    @JsonIgnore // property not in schema
    public boolean isNoTitle() {return this.notitle;}

    @JsonIgnore // Property not in schema
    @Nullable
    public String getHtmlClass() {return htmlClass;}

    public void setHtmlClass(@Nullable String htmlClass) {this.htmlClass = htmlClass;}

    public void setNotitle(boolean value) {this.notitle = value;}

    @JsonIgnore // Property not in schema
    @Nullable
    public String getFieldHtmlClass() {return fieldHtmlClass;}

    public void setFieldHtmlClass(@Nullable String fieldHtmlClass) {this.fieldHtmlClass = fieldHtmlClass;}

    @JsonIgnore // Property not in schema
    @Nullable
    public String getPrepend() {return prepend;}

    public void setPrepend(@Nullable String prepend) {this.prepend = prepend;}

    @JsonIgnore // Property not in schema
    @Nullable
    public String getAppend() {return append;}

    public void setAppend(@Nullable String append) {this.append = append;}

    @JsonIgnore // Property not in schema
    @Nullable
    public String getPlaceholder() {return placeholder;}

    public void setPlaceholder(@Nullable String placeholder) {this.placeholder = placeholder;}

    @JsonIgnore // Property not in schema
    public boolean isDisabled() {return disabled;}

    public void setDisabled(boolean disabled) {this.disabled = disabled;}

    @JsonIgnore // Property not in schema
    public boolean isReadonly() {return readonly;}

    public void setReadonly(boolean readonly) {this.readonly = readonly;}


    //
    // builders
    //

    public ElementSchema<T> title(String value) {
        setTitle(value);
        return this;
    }

    public ElementSchema<T> required(boolean value) {
        setRequired(value);
        return this;
    }

    public ElementSchema<T> description(String value) {
        setDescription(value);
        return this;
    }

    public ElementSchema<T> noTitle(boolean value) {
        setNotitle(value);
        return this;
    }

    public ElementSchema<T> htmlClass(String value) {
        setHtmlClass(value);
        return this;
    }

    public ElementSchema<T> fieldHtmlClass(String value) {
        setFieldHtmlClass(value);
        return this;
    }

    public ElementSchema<T> prepend(String value) {
        setPrepend(value);
        return this;
    }

    public ElementSchema<T> append(String value) {
        setAppend(value);
        return this;
    }

    public ElementSchema<T> placeholder(String value) {
        setPlaceholder(value);
        return this;
    }

    public ElementSchema<T> disabled(boolean value) {
        setDisabled(value);
        return this;
    }

    public ElementSchema<T> readOnly(boolean value) {
        setReadonly(value);
        return this;
    }


}
