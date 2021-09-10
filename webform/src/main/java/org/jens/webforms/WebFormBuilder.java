package org.jens.webforms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jens.webforms.ElementFormButton.ButtonType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;

/**
 * Verwalter und zusammensteller eines WebFormulars.
 *
 * @author Jens Ritter on 10/09/2021.
 */
public class WebFormBuilder {
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final Map<String, ElementSchema<?>> elements = new LinkedHashMap<>();
    private final Collection<ElementFormButton> buttons = new ArrayList<>();
    private String titleSubmit = "Submit";

    /**
     * Add another Control to the current JsonForm.
     * <p>
     * Does throw IllegalArgumentException, if the name already exists in the current form.
     *
     * @param name Name of the Control
     * @param control Control
     * @return
     */
    public WebFormBuilder add(String name, ElementSchema<?> control) {
        if(this.elements.containsKey(name)) {
            throw new IllegalArgumentException("Duplicate 'name' for Control");
        }
        this.elements.put(name, control);
        return this;
    }

    public ElementFormButton addButton(String id, String title) {
        ElementFormButton button = new ElementFormButton(ButtonType.button, title);
        this.buttons.add(button);
        return button;
    }


    public WebForm toWebForm() {
        return new WebForm(
            buildSchema(),
            buildForm()
        );
    }

    private Map<String, JsonSchema> buildSchema() {
        Map<String, JsonSchema> result = new LinkedHashMap<>();
        for(Entry<String, ElementSchema<?>> entry : elements.entrySet()) {

            ElementSchema<?> element = entry.getValue();
            JsonSchema jsonSchema = element.buildDefaultSchema();
            element.buildSchema(jsonSchema);
            result.put(entry.getKey(), jsonSchema);
        }
        return result;
    }

    private List<JsonForm> buildForm() {
        List<JsonForm> result = new ArrayList<>();

        for(Entry<String, ElementSchema<?>> entry : elements.entrySet()) {
            final ElementForm element = new ElementForm(entry.getKey()); // entry.getValue().getTitle()
            ElementSchema<?> elementSchema = entry.getValue();
            elementSchema.buildDefaultForm(element);
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


    public String toJson() {
        try {
            return objectMapper.writeValueAsString(toWebForm());
        } catch(JsonProcessingException e) {
            return "{\"error\": \"" + e + "\"}";
        }
    }

    //
    // beans : titleSubmit
    //
    public String getTitleSubmit() {return titleSubmit;}

    public void setTitleSubmit(String titleSubmit) {this.titleSubmit = titleSubmit;}

    public WebFormBuilder titleSubmit(String submit) {
        setTitleSubmit(submit);
        return this;
    }

    //
    // beans buttons
    //

    public Collection<ElementFormButton> getButtons() {return Collections.unmodifiableCollection(buttons);}

    public void setButtons(Collection<ElementFormButton> btn) {
        this.buttons.clear();
        this.buttons.addAll(btn);
    }


    //
    // elements
    //

    public Map<String, ElementSchema<?>> getElements() {return Collections.unmodifiableMap(elements);}

    public void setElements(Map<String, ElementSchema<?>> values) {
        elements.clear();
        elements.putAll(values);
    }
}
