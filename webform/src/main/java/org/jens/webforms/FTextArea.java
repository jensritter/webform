package org.jens.webforms;

/**
 * @author Jens Ritter on 05/09/2021.
 */
public class FTextArea extends FString {

    FTextArea() {}

    public FTextArea(String label) {
        super(label);
    }

    @Override
    public void buildForm(ElementForm element) {
        element.setType("textarea");
    }
}
