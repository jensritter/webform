
package org.jens.webforms;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

/**
 * @author Jens Ritter on 29/08/2021.
 */
public class FNumber extends ElementSchema<Number> {
    protected FNumber() {}

    ;

    public FNumber(String label) {
        super(FormType.FormNumber, label);
    }


    @Override
    protected void buildForm(ElementForm element) {
        // default-value not needed
    }

    @Override
    public void parseForm(JsonNode schemaElement, JsonNode formElement, Optional<JsonNode> defaultValueNode) {
        defaultValueNode.ifPresent(k -> setValue(k.asDouble()));
    }

    @Override
    void buildSchema(JsonSchema jsonSchema) {

    }

    //
    // beans
    //

    public void setValue(@Nullable Number number) {setDefaultValue(number);}

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
