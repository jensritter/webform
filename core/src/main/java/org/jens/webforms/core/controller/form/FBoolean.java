
package org.jens.webforms.core.controller.form;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Jens Ritter on 29/08/2021.
 */
public class FBoolean extends Control {
    @JsonIgnore
    private String inlineTitle;

    public FBoolean(String label) {
        super(FormType.FormBoolean, label);
    }

    public FBoolean(String label, String inlinetitle) {
        this(label);
        this.inlineTitle = inlinetitle;
    }

    public String getInlineTitle() {return inlineTitle;}

    public void setInlineTitle(String inlineTitle) {this.inlineTitle = inlineTitle;}
}
