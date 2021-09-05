
package org.jens.webforms.core.controller.form;

/**
 * @author Jens Ritter on 29/08/2021.
 */
public class FDate extends ElementControl {
    public FDate(String label) {
        super(FormType.FormString, label);
    }

    @Override
    ElementForm buildForm(ElementForm element) {
        element.setType("date");
        return element;
    }
}
