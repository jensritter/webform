package org.jens.webforms.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jens.webforms.core.ElementFormButton.ButtonType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;

/**
 * Json-Response für Controller, damit JS daraus ein Formular baut.
 *
 * @author Jens Ritter on 29/08/2021.
 */
public class JsonForm {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Map<String, ElementSchema<?>> schema = new LinkedHashMap<>();
    private final List<ElementFormButton> buttons = new ArrayList<>();
    private String titleSubmit = "Submit";

    /**
     * Add another Control to the current Form.
     * <p>
     * Does throw IllegalArgumentException, if the name already exists in the current form.
     *
     * @param name Name of the Control
     * @param control Control
     */
    public void add(String name, ElementSchema<?> control) {
        if(this.schema.containsKey(name)) {
            throw new IllegalArgumentException("Duplicate 'name' for Control");
        }
        this.schema.put(name, control);
    }

    @JsonProperty("form")
    public List<ElementFormAbstract> getForm() {
        List<ElementFormAbstract> result = new ArrayList<>();

        for(Entry<String, ElementSchema<?>> entry : schema.entrySet()) {
            final ElementForm element = new ElementForm(entry.getKey()); // entry.getValue().getTitle()
            ElementSchema<?> elementSchema = entry.getValue();
            elementSchema.buildForm(element);
            result.add(element);
        }
        if(noSubmitButtonPresent()) {
            // add a Submitbutton last
            buttons.add(new ElementFormButton(ButtonType.submit, titleSubmit));
        }
        result.addAll(buttons);
        return result;
    }

    private boolean noSubmitButtonPresent() {
        Optional<ElementFormButton> any = buttons.stream()
            .filter(it -> Objects.equals(it.getType(), ButtonType.submit.toString()))
            .findAny();
        return any.isEmpty();
    }

    @JsonProperty("schema")
    public Map<String, ElementSchema<?>> getSchema() {return Collections.unmodifiableMap(schema);}

    @JsonIgnore
    public String getTitleSubmit() {return titleSubmit;}

    public void setTitleSubmit(String titleSubmit) {this.titleSubmit = titleSubmit;}


    /** Damit man JsonForm in eine Webseite als "json" einbetten kann **/
    @Override
    public String toString() {
        try {
            return objectMapper.writeValueAsString(this);
        } catch(JsonProcessingException e) {
            return "{\"error\": \"" + e + "\"}";
        }
    }

    public ElementFormButton addButton(String id, String title) {
        ElementFormButton button = new ElementFormButton(ButtonType.button, title);
        this.buttons.add(button);
        return button;
    }
}
