
package org.jens.webforms.core;

import java.util.Optional;

/**
 * @author Jens Ritter on 29/08/2021.
 */
public class FNumber extends ElementSchema<Number> {
    public FNumber(String label) {
        super(FormType.FormNumber, label);
    }

    @Override
    public ElementSchema<Number> value(Number value) {
        setDefaultValue(Optional.ofNullable(value));
        return this;
    }
}
