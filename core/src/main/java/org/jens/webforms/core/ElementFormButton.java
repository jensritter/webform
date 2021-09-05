package org.jens.webforms.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Form-Buttons
 * https://github.com/jsonform/jsonform/wiki#fields-buttonss
 *
 * @author Jens Ritter on 05/09/2021.
 */
@JsonInclude(Include.NON_NULL)
public class ElementFormButton extends ElementFormAbstract {

    private final ButtonType type;
    private String title;

    ElementFormButton(ButtonType type) {
        this.type = type;
    }

    /**
     * Creates the Submit-Button
     *
     * @param buttonTitle
     * @return
     */
    static ElementFormButton submit(String buttonTitle) {
        ElementFormButton elementFormButton = new ElementFormButton(ButtonType.submit);
        elementFormButton.setTitle(buttonTitle);
        return elementFormButton;
    }

    public enum ButtonType {
        submit("submit"),
        button("button");

        private final String name;

        ButtonType(String name) {
            this.name = name;
        }

        @JsonValue
        public String toString() {
            return name;
        }
    }

    @Override
    public String getType() {return type.toString();}

    public String getTitle() {return title;}

    void setTitle(String title) {this.title = title;}
}
