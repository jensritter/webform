
package org.jens.webforms.core;

import org.jetbrains.annotations.Nullable;

/**
 * @author Jens Ritter on 29/08/2021.
 */
public class FNumber extends ElementSchema<Number> {
    public FNumber(String label) {
        super(FormType.FormNumber, label);
    }

    @Override
    public ElementSchema<Number> value(@Nullable Number value) {
        setDefaultValue(value);
        return this;
    }
}
