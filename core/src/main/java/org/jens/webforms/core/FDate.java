
package org.jens.webforms.core;

import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;
import java.util.Optional;

/**
 * @author Jens Ritter on 29/08/2021.
 */
public class FDate extends ElementSchema<LocalDate> {
    public FDate(String label) {
        super(FormType.FormString, label);
    }

    @Override
    ElementForm buildForm(ElementForm element) {
        element.setType("date");
        return element;
    }

    @Override
    public ElementSchema<LocalDate> setValue(@Nullable LocalDate value) {
        setDefaultValue(Optional.ofNullable(value));
        return this;
    }

}
