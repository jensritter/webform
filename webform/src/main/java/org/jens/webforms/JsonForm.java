package org.jens.webforms;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.jetbrains.annotations.Nullable;

/**
 * Pojo für WebForm-FORM
 *
 * @author Jens Ritter on 05/09/2021.
 */
@JsonInclude(Include.NON_NULL)
class JsonForm {

    @Nullable
    private String type;

    public @Nullable String getType() {return type;}

    void setType(@Nullable String type) {this.type = type;}
}
