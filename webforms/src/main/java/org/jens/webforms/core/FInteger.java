
package org.jens.webforms.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

/**
 * @author Jens Ritter on 29/08/2021.
 */
public class FInteger extends ElementSchema<Integer> {
    public FInteger(String label) {
        super(FormType.FormInteger, label);
    }

    @Override
    protected void buildForm(ElementForm element) {
        // default-value not needed
    }

    @Override
    public void parseForm(JsonNode schemaElement, JsonNode formElement, Optional<JsonNode> defaultValueNode) {
        defaultValueNode.ifPresent(k -> {
            setValue(k.asInt());
        });
    }


    @JsonIgnore
    @Nullable
    public Integer getValue() {
        return getDefaultValue() != null ? (Integer) getDefaultValue() : null;
    }

    public void setValue(@Nullable Integer value) {setDefaultValue(value);}

    @Override
    public ElementSchema<Integer> value(@Nullable Integer value) {
        setValue(value);
        return this;
    }


}
