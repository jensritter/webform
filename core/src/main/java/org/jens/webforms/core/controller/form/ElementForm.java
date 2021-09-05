package org.jens.webforms.core.controller.form;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Json-Pojo für
 *
 * @author Jens Ritter on 05/09/2021.
 */ // https://github.com/jsonform/jsonform/wiki#common-field-properties
// https://github.com/jsonform/jsonform/wiki#a-number-the-range-type
@JsonInclude(Include.NON_EMPTY)
public class ElementForm extends ElementFormAbstract {

    private final String key;
    private final Map<String, String> titleMap = new HashMap<>();


    private String inlinetitle;

    public ElementForm(String key) {
        this.key = key;
    }

    void addTitleMaps(Map<String, String> values) {
        this.titleMap.putAll(values);
    }

    public String getKey() {return key;}

    public Map<String, String> getTitleMap() {return Collections.unmodifiableMap(titleMap);}

    public String getInlinetitle() {return inlinetitle;}

    public void setInlinetitle(String inlinetitle) {this.inlinetitle = inlinetitle;}
}
