package org.jens.webforms.core.controller.form;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jens Ritter on 05/09/2021.
 */ // https://github.com/jsonform/jsonform/wiki#common-field-properties
// https://github.com/jsonform/jsonform/wiki#a-number-the-range-type
@JsonInclude(Include.NON_EMPTY)
public class ElementForm {
    private final String key;
    private final Map<String, String> titleMap = new HashMap<>();
    private String type;
    private final String title;


    private String inlinetitle;

    public static ElementForm submit(String label) {
        return new ElementForm(null, label, "submit");
    }

    public ElementForm(String key) {
        this.key = key;
        this.title = null;
        this.type = null;
    }

    public ElementForm(String key, String label) {
        this.key = key;
        this.title = label;
        this.type = null;
    }

    public ElementForm(String key, String title, String type) {
        this.key = key;
        this.title = title;
        this.type = type;
    }

    void addTitleMaps(Map<String, String> values) {
        this.titleMap.putAll(values);
    }

    public String getKey() {return key;}

    public Map<String, String> getTitleMap() {return titleMap;}

    public String getType() {return type;}

    public void setType(String type) {this.type = type;}

    public String getTitle() {return title;}

    public String getInlinetitle() {return inlinetitle;}

    public void setInlinetitle(String inlinetitle) {this.inlinetitle = inlinetitle;}
}
