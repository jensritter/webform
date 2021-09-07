package org.jens.webforms.core;

/**
 * @author Jens Ritter on 05/09/2021.
 */
public class FTextArea extends FString {
    protected FTextArea(String label) {
        super(label);
    }

    @Override
    ElementForm buildForm(ElementForm element) {
        element.setType("textarea");
        return element;
    }
}