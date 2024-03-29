package org.jens.webforms;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * JsonForm-Buttons
 * https://github.com/jsonform/jsonform/wiki#fields-buttonss
 * https://github.com/jsonform/jsonform/wiki#group-of-buttons-the-actions-type
 *
 * @author Jens Ritter on 05/09/2021.
 */
@JsonInclude(Include.NON_DEFAULT)
public class ElementFormButton extends JsonForm {

    private final ButtonType type;
    private final String title;

    ElementFormButton(ButtonType type, String title) {
        this.type = type;
        this.title = title;
    }

    @Override
    public String getType() {return type.toString();}

    public String getTitle() {return title;}


    enum ButtonType {
        submit("submit"),
        button("button");

        private final String name;

        ButtonType(String name) {
            this.name = name;
        }

        @Override
        @JsonValue
        public String toString() {
            return name;
        }
    }


}
