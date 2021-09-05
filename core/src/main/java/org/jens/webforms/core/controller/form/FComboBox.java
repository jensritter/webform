package org.jens.webforms.core.controller.form;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://github.com/jsonform/jsonform/wiki#selection-fields
 *
 * @author Jens Ritter on 29/08/2021.
 */
public class FComboBox extends Control {
    @JsonProperty("enum")
    private List<ComboElement> values = new ArrayList<>();


    public FComboBox(String label) {
        super(FormType.FormString, label);
    }

    @JsonProperty("enum")
    public List<String> getEnums() {
        return values.stream().map(ComboElement::getId).collect(Collectors.toList());
    }

    public void addValues(List<String> werte) {
        this.values.clear();
        for(int i = 0; i < werte.size(); i++) {
            values.add(new ComboElement(Integer.toString(i), werte.get(i)));
        }
    }


    @JsonIgnore
    public List<ComboElement> getValues() {return values;}

    public void setValues(List<ComboElement> values) {this.values = values;}


    public static class ComboElement {
        private final String id;
        private final String name;

        public ComboElement(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {return id;}

        public String getName() {return name;}
    }
}
