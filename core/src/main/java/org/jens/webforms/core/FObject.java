package org.jens.webforms.core;

/**
 * @author Jens Ritter on 29/08/2021.
 */
public class FObject extends ElementSchema<Object> {
    public FObject(String label) {
        super(FormType.FormObject, label);
    }

    @Override
    public ElementSchema<Object> setValue(Object value) {
        throw new IllegalStateException("unimplemented: ");
    }
}
