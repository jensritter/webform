package org.jens.webforms;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Optional;

/**
 * @author Jens Ritter on 29/08/2021.
 */
public class FObject extends ElementSchema<Object> {
    FObject() {}

    public FObject(String label) {
        super(FormType.FormObject, label);
    }

    @Override
    protected void buildForm(ElementForm element) {
        // default-value not needed
    }

    @Override
    protected void parseForm(JsonNode schemaElement, JsonNode formElement, Optional<JsonNode> defaultValueNode) {
        defaultValueNode.ifPresent(this::setDefaultValue);
        throw new IllegalStateException("unimplemented: ");
    }

    @Override
    void buildSchema(JsonSchema jsonSchema) {}


}
