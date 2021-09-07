package org.jens.webforms.core;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

/**
 * @author Jens Ritter on 29/08/2021.
 */
public class FArray extends ElementSchema<List<String>> {
    public FArray(String label) {
        super(FormType.FormArray, label);
    }


    @Override
    public ElementSchema<List<String>> value(@Nullable List<String> value) {
        throw new IllegalStateException("unimplemented: ");
    }

    @Override
    void parseForm(JsonNode schemaElement, JsonNode formElement, Optional<JsonNode> defaultValueNode) {
        defaultValueNode.ifPresent(k -> {
            setDefaultValue(k);
        });
    }
}
