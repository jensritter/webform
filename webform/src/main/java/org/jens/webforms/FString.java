package org.jens.webforms;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Optional;

/**
 * @author Jens Ritter on 29/08/2021.
 */
public class FString extends ElementSchema<String> {

    FString() {}

    public FString(String label) {
        super(FormType.FormString, label);
    }

    @Override
    public void parseForm(JsonNode schemaElement, JsonNode formElement, Optional<JsonNode> defaultValueNode) {
        defaultValueNode.ifPresent(key->setDefaultValue(key.asText()));
    }

    @Override
    void buildSchema(JsonSchema jsonSchema) {}

    @Override
    protected void buildForm(ElementForm element) {
    }

}
