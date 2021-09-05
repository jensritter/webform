package org.jens.webforms.core;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author Jens Ritter on 29/08/2021.
 */
public class FArray extends ElementSchema<List<String>> {
    public FArray(String label) {
        super(FormType.FormArray, label);
    }

    @Override
    public ElementSchema<List<String>> setValue(@Nullable List<String> value) {
        throw new IllegalStateException("unimplemented: ");
    }
}
