
package org.jens.webforms.core;

/**
 * @author Jens Ritter on 29/08/2021.
 */
public class FDate extends ElementSchema {
    public FDate(String label) {
        super(FormType.FormString, label);
    }

    @Override
    ElementForm buildForm(ElementForm element) {
        element.setType("date");
        return element;
    }
}
