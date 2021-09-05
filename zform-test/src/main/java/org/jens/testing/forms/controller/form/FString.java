package org.jens.testing.forms.controller.form;

/**
 * @author Jens Ritter on 29/08/2021.
 */
public class FString extends Control {
    public FString(String label) {
        super(FormType.FormString, label);
    }

    public FString(String label, boolean required) {
        super(FormType.FormString, label, required);
    }

}
