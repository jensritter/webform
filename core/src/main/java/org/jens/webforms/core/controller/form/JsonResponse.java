package org.jens.webforms.core.controller.form;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jens.webforms.core.controller.form.FComboBox.ComboElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Jens Ritter on 29/08/2021.
 */
public class JsonResponse {
    private Map<String, Control> schema = new LinkedHashMap<>();

    @JsonProperty("form")
    public List<FormElement> getForm() {
        List<FormElement> result = new ArrayList<>();

        for(Entry<String, Control> entry : schema.entrySet()) {
            FormElement el = new FormElement(entry.getKey()); // entry.getValue().getTitle()
            result.add(el);

            if(entry.getValue() instanceof FComboBox) {
                FComboBox cast = FComboBox.class.cast(entry.getValue());
                Map<Integer, String> werte = new HashMap<>();

                for(ComboElement value : cast.getValues()) {
                    werte.put(Integer.parseInt(value.getId()), value.getName());
                }
                el.addTitleMaps(werte);
            }
            if(entry.getValue() instanceof FDate) {
                el.setType("date");
            }
            if(entry.getValue() instanceof FBoolean) {
                FBoolean cast = FBoolean.class.cast(entry.getValue());
                el.setInlinetitle(cast.getInlineTitle());
            }
        }
        result.add(FormElement.submit("Submit"));
        return result;
    }

    @JsonProperty("schema")
    public Map<String, Control> getSchema() {return schema;}

    public void setSchema(Map<String, Control> schema) {this.schema = schema;}

    public void add(String name, Control name1) {
        this.schema.put(name, name1);
    }


    // https://github.com/jsonform/jsonform/wiki#common-field-properties
    // https://github.com/jsonform/jsonform/wiki#a-number-the-range-type
    // https://github.com/jsonform/jsonform/wiki#a-number-the-range-type
    @JsonInclude(Include.NON_EMPTY)
    public static class FormElement {
        private final String key;
        private final Map<Integer, String> titleMap = new HashMap<>();
        private String type;
        private final String title;


        private String inlinetitle;

        public static FormElement submit(String label) {
            return new FormElement(null, label, "submit");
        }

        public FormElement(String key) {
            this.key = key;
            this.title = null;
            this.type = null;
        }

        public FormElement(String key, String label) {
            this.key = key;
            this.title = label;
            this.type = null;
        }

        public FormElement(String key, String title, String type) {
            this.key = key;
            this.title = title;
            this.type = type;
        }

        void addTitleMaps(Map<Integer, String> values) {
            this.titleMap.putAll(values);
        }

        public String getKey() {return key;}

        public Map<Integer, String> getTitleMap() {return titleMap;}

        public String getType() {return type;}

        public void setType(String type) {this.type = type;}

        public String getTitle() {return title;}

        public String getInlinetitle() {return inlinetitle;}

        public void setInlinetitle(String inlinetitle) {this.inlinetitle = inlinetitle;}
    }

}
