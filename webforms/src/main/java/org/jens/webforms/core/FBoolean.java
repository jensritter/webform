
package org.jens.webforms.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.jetbrains.annotations.Nullable;

/**
 * @author Jens Ritter on 29/08/2021.
 */
public class FBoolean extends ElementSchema<Boolean> {

    private String inlineTitle;

    public FBoolean(String label) {
        super(FormType.FormBoolean, label);
    }

    public FBoolean(String label, String value) {
        this(label);
        this.inlineTitle = value;
    }

    @Override
    ElementForm buildForm(ElementForm element) {
        element.setInlineTitle(inlineTitle);
        return element;
    }

    @Override
    public ElementSchema<Boolean> value(@Nullable Boolean value) {
        setDefaultValue(value);
        return this;

    }

    @JsonIgnore // not in Schema
    public String getInlineTitle() {return inlineTitle;}

    public void setInlineTitle(String inlineTitle) {this.inlineTitle = inlineTitle;}

    public FBoolean inlineTitle(String value) {
        setInlineTitle(value);
        return this;
    }

}
