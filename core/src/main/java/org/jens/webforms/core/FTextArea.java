package org.jens.webforms.core;

/**
 * @author Jens Ritter on 05/09/2021.
 */
public class FTextArea extends ElementSchema {
    protected FTextArea(String label) {
        super(FormType.FormString, label);
    }

    @Override
    ElementForm buildForm(ElementForm element) {
        element.setType("textarea");
        return element;
    }
}
