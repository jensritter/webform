package org.jens.webforms.core;

/**
 * @author Jens Ritter on 05/09/2021.
 */
public class FTextArea extends FString {

    public FTextArea(String label) {
        super(label);
    }

    @Override
    void buildForm(ElementForm element) {
        element.setType("textarea");
    }
}
