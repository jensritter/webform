
package org.jens.webforms;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

/**
 * @author Jens Ritter on 29/08/2021.
 */
public class FBoolean extends ElementSchema<Boolean> {

    private @Nullable String inlineTitle;

    protected FBoolean() {}

    public FBoolean(String label) {
        super(FormType.FormBoolean, label);
    }

    public FBoolean(String label, @Nullable String value) {
        this(label);
        this.inlineTitle = value;
    }

    @Override
    protected void buildForm(ElementForm element) {
        // default-value not needed
        element.setInlineTitle(inlineTitle);
    }

    @Override
    public void parseForm(JsonNode schemaElement, JsonNode formElement, Optional<JsonNode> defaultValueNode) {
        // form: {"key":"bool","inlinetitle":"inlinetitle"}
        setInlineTitle(parseValueAsString(formElement, "inlinetitle"));
        defaultValueNode.ifPresent(k -> value(k.asBoolean()));
    }

    @Override
    void buildSchema(JsonSchema jsonSchema) {

    }

    public boolean getValue() {
        return getDefaultValue() != null ? (Boolean) getDefaultValue() : false;
    }

    public void setValue(@Nullable Boolean value) {setDefaultValue(value);}

    @Override
    public ElementSchema<Boolean> value(@Nullable Boolean value) {
        setValue(value);
        return this;
    }

    @Nullable
    public String getInlineTitle() {return inlineTitle;}

    public void setInlineTitle(@Nullable String inlineTitle) {this.inlineTitle = inlineTitle;}

    public FBoolean inlineTitle(@Nullable String value) {
        setInlineTitle(value);
        return this;
    }

}
