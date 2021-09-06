
package org.jens.webforms.core;

import org.jetbrains.annotations.Nullable;

/**
 * @author Jens Ritter on 29/08/2021.
 */
public class FInteger extends ElementSchema<Integer> {
    public FInteger(String label) {
        super(FormType.FormInteger, label);
    }

    @Override
    public ElementSchema<Integer> value(@Nullable Integer value) {
        setDefaultValue(value);
        return this;
    }
}
