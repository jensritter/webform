package org.jens.webforms.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

/**
 * @author Jens Ritter on 29/08/2021.
 */
public class FString extends ElementSchema<String> {
    public FString(String label) {
        super(FormType.FormString, label);
    }

    @Override
    public void parseForm(JsonNode schemaElement, JsonNode formElement, Optional<JsonNode> defaultValueNode) {
        defaultValueNode.ifPresent(k -> setDefaultValue(k.asText()));
    }

    @Override
    protected void buildForm(ElementForm element) {
        // Default-Value not needed
    }


    // bean

    @JsonIgnore
    @Nullable
    public String getValue() {
        return getDefaultValue() != null ? (String) getDefaultValue() : null;
    }

    public void setValue(@Nullable String value) {
        setDefaultValue(value);
    }

    //
    // builder
    //


    @Override
    public FString value(@Nullable String value) {
        setValue(value);
        return this;
    }
}
