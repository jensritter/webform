package org.jens.webforms.core.controller.form;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * https://github.com/jsonform/jsonform/wiki#selection-fields
 *
 * @author Jens Ritter on 29/08/2021.
 */
public class FComboBox extends ElementControl {
    private final Map<String, String> values = new LinkedHashMap<>();

    public FComboBox(String label) {
        super(FormType.FormString, label);
    }

    @JsonProperty("enum")
    public Collection<String> getEnums() {return values.keySet();}


    @Override
    ElementForm buildForm(ElementForm element) {
        element.addTitleMaps(values);
        return element;
    }

    public void addValues(List<String> werte) {
        this.values.clear();
        for(int i = 0; i < werte.size(); i++) {
            values.put(Integer.toString(i), werte.get(i));
        }
    }

    public void addValues(Map<String, String> newValues) {
        this.values.clear();
        this.values.putAll(newValues);
    }

    public void addValue(String id, String value) {
        this.values.put(id, value);
    }

}
