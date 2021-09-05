package org.jens.webforms.core;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Jens Ritter on 05/09/2021.
 */
public class FRange extends ElementControl {
    private int minimum;
    private int maximum;

    private int formStep;
    private boolean formIndicator;

    protected FRange(String label) {
        super(FormType.FormInteger, label);
    }

    public int getMinimum() {return minimum;}

    public FRange setMinimum(int minimum) {
        this.minimum = minimum;
        return this;
    }

    public int getMaximum() {return maximum;}

    public FRange setMaximum(int maximum) {
        this.maximum = maximum;
        return this;
    }

    @JsonIgnore
    public int getFormStep() {return formStep;}

    public void setFormStep(int formStep) {this.formStep = formStep;}

    @JsonIgnore
    public boolean isFormIndicator() {return formIndicator;}

    public void setFormIndicator(boolean formIndicator) {this.formIndicator = formIndicator;}

    @Override
    ElementForm buildForm(ElementForm element) {
        element.setType("range");
        element.setStep(formStep);
        element.setIndicator(formIndicator);
        return element;
    }
}
