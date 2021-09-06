package org.jens.webforms.core;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Jens Ritter on 05/09/2021.
 */
public class FRange extends FInteger {
    private int minimum;
    private int maximum;

    private int formStep;
    private boolean formIndicator;

    public FRange(String label) {super(label);}

    @Override
    ElementForm buildForm(ElementForm element) {
        element.setType("range");
        element.setStep(formStep);
        element.setIndicator(formIndicator);
        return element;
    }


    // beans

    public int getMinimum() {return minimum;}

    public void setMinimum(int minimum) {this.minimum = minimum;}

    public int getMaximum() {return maximum;}

    public void setMaximum(int maximum) {this.maximum = maximum;}

    @JsonIgnore
    public int getFormStep() {return formStep;}

    public void setFormStep(int formStep) {this.formStep = formStep;}

    @JsonIgnore
    public boolean isFormIndicator() {return formIndicator;}

    public void setFormIndicator(boolean formIndicator) {this.formIndicator = formIndicator;}

    // builder

    // builder
    public FRange minimum(int value) {
        setMinimum(value);
        return this;
    }

    public FRange maximum(int max) {
        setMaximum(max);
        return this;
    }

    public FRange formStep(int step) {
        setFormStep(step);
        return this;
    }

    public FRange formIndicator(boolean value) {
        setFormIndicator(value);
        return this;
    }

}
