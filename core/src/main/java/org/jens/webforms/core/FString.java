package org.jens.webforms.core;

import org.jetbrains.annotations.Nullable;

import java.util.Optional;

/**
 * @author Jens Ritter on 29/08/2021.
 */
public class FString extends ElementSchema<String> {
    public FString(String label) {
        super(FormType.FormString, label);
    }

    @Override
    public FString value(@Nullable String value) {
        setDefaultValue(Optional.ofNullable(value));
        return this;
    }
}
