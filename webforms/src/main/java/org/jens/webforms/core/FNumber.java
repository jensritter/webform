
package org.jens.webforms.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

/**
 * @author Jens Ritter on 29/08/2021.
 */
public class FNumber extends ElementSchema<Number> {
    public FNumber(String label) {
        super(FormType.FormNumber, label);
    }


    @Override
    protected void buildForm(ElementForm element) {
        // default-value not needed
    }

    @Override
    public void parseForm(JsonNode schemaElement, JsonNode formElement, Optional<JsonNode> defaultValue) {
        defaultValue.ifPresent(k -> {
            setValue(k.asDouble());
        });
    }

    //
    // beans
    //

    public void setValue(@Nullable Number number) {
        setDefaultValue(number);
    }

    @JsonIgnore
    @Nullable
    public Number getValue() {
        return (Number) getDefaultValue();
    }

    //
    // builder
    //
    @Override
    public ElementSchema<Number> value(@Nullable Number value) {
        setValue(value);
        return this;
    }

}
