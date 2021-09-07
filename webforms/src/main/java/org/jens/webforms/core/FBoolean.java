
package org.jens.webforms.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

/**
 * @author Jens Ritter on 29/08/2021.
 */
public class FBoolean extends ElementSchema<Boolean> {

    private @Nullable String inlineTitle;

    public FBoolean(String label) {
        super(FormType.FormBoolean, label);
    }

    public FBoolean(String label, @Nullable String value) {
        this(label);
        this.inlineTitle = value;
    }

    @Override
    void buildForm(ElementForm element) {
        element.setInlineTitle(inlineTitle);
    }

    @Override
    public void parseForm(JsonNode schemaElement, JsonNode formElement, Optional<JsonNode> defaultValueNode) {
        // form: {"key":"bool","inlinetitle":"inlinetitle"}
        setInlineTitle(getValueAsString(formElement, "inlinetitle"));
        defaultValueNode.ifPresent(k -> value(k.asBoolean()));
    }

    @Override
    public ElementSchema<Boolean> value(@Nullable Boolean value) {
        setDefaultValue(value);
        return this;
    }

    @JsonIgnore // not in Schema
    @Nullable
    public String getInlineTitle() {return inlineTitle;}

    public void setInlineTitle(@Nullable String inlineTitle) {this.inlineTitle = inlineTitle;}

    public FBoolean inlineTitle(@Nullable String value) {
        setInlineTitle(value);
        return this;
    }

}
