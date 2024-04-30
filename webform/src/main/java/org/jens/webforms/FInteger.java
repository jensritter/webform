
package org.jens.webforms;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Optional;

/**
 * @author Jens Ritter on 29/08/2021.
 */
public class FInteger extends ElementSchema<Integer> {
    FInteger() {}

    public FInteger(String label) {
        super(FormType.FormInteger, label);
    }

    @Override
    protected void buildForm(ElementForm element) {
        // default-value not needed
    }

    @Override
    protected void parseForm(JsonNode schemaElement, JsonNode formElement, Optional<JsonNode> defaultValueNode) {
        defaultValueNode.ifPresent(key->setDefaultValue(key.asInt()));
    }

    @Override
    void buildSchema(JsonSchema jsonSchema) {}

}
