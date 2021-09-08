package org.jens.webforms.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Optional;

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
    protected void buildForm(ElementForm element) {
        // default-value not needed
        element.setType("range");
        element.setStep(formStep);
        element.setIndicator(formIndicator);
    }

    @Override
    public void parseForm(JsonNode schemaElement, JsonNode formElement, Optional<JsonNode> defaultValueNode) {
        super.parseForm(schemaElement, formElement, defaultValueNode);
        JsonNode nodeMinimum = schemaElement.get("minimum");
        if(nodeMinimum != null) {
            setMinimum(nodeMinimum.asInt());
        }
        JsonNode nodeMaximum = schemaElement.get("maximum");
        if(nodeMaximum != null) {
            setMaximum(nodeMaximum.asInt());
        }

        JsonNode nodeStep = formElement.get("step");
        if(nodeStep != null) {
            setFormStep(nodeStep.asInt());
        }
        JsonNode nodeIndicator = formElement.get("indicator");
        if(nodeIndicator != null) {
            setFormIndicator(nodeIndicator.asBoolean());
        }


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
