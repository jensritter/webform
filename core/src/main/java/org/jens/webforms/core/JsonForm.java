package org.jens.webforms.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Json-Response für Controller, damit JS daraus ein Formular baut.
 *
 * @author Jens Ritter on 29/08/2021.
 */
public class JsonForm {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Map<String, ElementSchema> schema = new LinkedHashMap<>();

    private String titleSubmit = "Submit";

    public String toString() {
        try {
            return objectMapper.writeValueAsString(this);
        } catch(JsonProcessingException e) {
            return "{\"error\": \"" + e + "\"}";
        }
    }

    @JsonProperty("form")
    public List<ElementFormAbstract> getForm() {
        List<ElementFormAbstract> result = new ArrayList<>();

        for(Entry<String, ElementSchema> entry : schema.entrySet()) {
            final ElementForm element = new ElementForm(entry.getKey()); // entry.getValue().getTitle()
            ElementForm elementForm = entry.getValue().buildForm(element);
            result.add(elementForm);
        }
        result.add(ElementFormButton.submit(titleSubmit));
        return result;
    }

    @JsonProperty("schema")
    public Map<String, ElementSchema> getSchema() {return Collections.unmodifiableMap(schema);}

    /**
     * Add another Control to the current Form.
     * <p>
     * Does throw IllegalArgumentException, if the name already exists in the current form.
     *
     * @param name Name of the Control
     * @param control Control
     */
    public void add(String name, ElementSchema control) {
        if(this.schema.containsKey(name)) {
            throw new IllegalArgumentException("Duplicate 'name' for Control");
        }
        this.schema.put(name, control);
    }

    @JsonIgnore
    public String getTitleSubmit() {return titleSubmit;}

    public void setTitleSubmit(String titleSubmit) {this.titleSubmit = titleSubmit;}
}
