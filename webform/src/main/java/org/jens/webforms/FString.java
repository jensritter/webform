package org.jens.webforms;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

/**
 * @author Jens Ritter on 29/08/2021.
 */
public class FString extends ElementSchema<String> {

    protected FString() {}

    public FString(String label) {
        super(FormType.FormString, label);
    }

    @Override
    public void parseForm(JsonNode schemaElement, JsonNode formElement, Optional<JsonNode> defaultValueNode) {
        defaultValueNode.ifPresent(k -> setDefaultValue(k.asText()));
    }

    @Override
    void buildSchema(JsonSchema jsonSchema) {

    }

    @Override
    protected void buildForm(ElementForm element) {
        // Default-Value not needed
    }


    // bean

    @Nullable
    public String getValue() {
        return getDefaultValue() != null ? (String) getDefaultValue() : null;
    }

    public void setValue(@Nullable String value) {setDefaultValue(value);}

    //
    // builder
    //


    @Override
    public FString value(@Nullable String value) {
        setValue(value);
        return this;
    }
}
