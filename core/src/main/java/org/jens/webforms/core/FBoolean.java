
package org.jens.webforms.core;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Jens Ritter on 29/08/2021.
 */
public class FBoolean extends ElementSchema {

    private String inlineTitle;

    private FBoolean(String label) {
        super(FormType.FormBoolean, label);
    }

    public FBoolean(String label, String inlinetitle) {
        this(label);
        this.inlineTitle = inlinetitle;
    }

    @JsonIgnore
    public String getInlineTitle() {return inlineTitle;}

    public void setInlineTitle(String inlineTitle) {this.inlineTitle = inlineTitle;}

    @Override
    ElementForm buildForm(ElementForm element) {
        element.setInlineTitle(inlineTitle);
        return element;
    }
}
