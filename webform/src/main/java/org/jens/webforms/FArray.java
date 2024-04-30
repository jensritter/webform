package org.jens.webforms;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.*;

/**
 * @author Jens Ritter on 29/08/2021.
 */
public class FArray extends ElementSchema<List<String>> {

    FArray() {}

    public FArray(String label) {
        super(FormType.FormArray, label);
    }

    @Override
    protected void buildForm(ElementForm element) {
        // default-value not needed
    }

    @Override
    protected void parseForm(JsonNode schemaElement, JsonNode formElement, Optional<JsonNode> defaultValueNode) {
        defaultValueNode.ifPresent(key->{
            Iterator<JsonNode> iterator = key.iterator();
            Collection<String> values = new ArrayList<>();
            while (iterator.hasNext()) {
                JsonNode next = iterator.next();
                values.add(next.asText());
            }
            setDefaultValue(values);
        });
    }

    @Override
    void buildSchema(JsonSchema jsonSchema) {}

}
