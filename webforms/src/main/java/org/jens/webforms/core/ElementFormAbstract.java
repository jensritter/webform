package org.jens.webforms.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.jetbrains.annotations.Nullable;

/**
 * @author Jens Ritter on 05/09/2021.
 */
@JsonInclude(Include.NON_NULL)
class ElementFormAbstract {

    @Nullable
    private String type;

    public @Nullable String getType() {return type;}

    void setType(@Nullable String type) {this.type = type;}
}
