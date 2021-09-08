package org.jens.webforms.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

/**
 * https://github.com/jsonform/jsonform/wiki#selection-fields
 *
 * @author Jens Ritter on 29/08/2021.
 */
public class FComboBox extends ElementSchema<String> {
    private final Logger logger = LoggerFactory.getLogger(FComboBox.class);

    private final Map<String, String> selectionValues = new LinkedHashMap<>();
    private boolean viewAsRadios;


    public FComboBox(String label) {
        super(FormType.FormString, label);
    }

    public FComboBox(String label, Collection<String> selectionValues) {
        this(label);
        this.selectionValues.putAll(buildWithIndex(selectionValues));
    }

    public FComboBox(String label, Map<String, String> selectionValues) {
        this(label);
        this.selectionValues.putAll(selectionValues);
    }

    @Override
    protected void buildForm(ElementForm element) {
        // default-value not needed
        element.setTitleMaps(selectionValues);
        if(viewAsRadios) {
            element.setType("radios");
        }
    }

    @Override
    public void parseForm(JsonNode schemaElement, JsonNode formElement, Optional<JsonNode> defaultValue) {
        defaultValue.ifPresent(k -> value(k.asText()));

        JsonNode type = formElement.get("type");
        setViewAsRadios(type != null && "radios".equals(type.asText()));

        JsonNode titleMap = formElement.get("titleMap");
        if(titleMap == null) {throw new IllegalStateException("unimplemented: no titleMap property. Condition in ElementSchema not met");}

        this.selectionValues.clear();
        Iterator<Entry<String, JsonNode>> fields = titleMap.fields();
        if(fields == null) {
            logger.warn("titleMap contains no elements {}", formElement);
        } else {
            while(fields.hasNext()) {
                Entry<String, JsonNode> next = fields.next();
                String key = next.getKey();
                JsonNode value = next.getValue();
                selectionValues.put(key, value != null ? value.asText() : null);
            }
        }
    }

    private static Map<String, String> buildWithIndex(Collection<String> werte) {
        Map<String, String> result = new LinkedHashMap<>();
        Iterator<String> iterator = werte.iterator();
        int i = 0;
        while(iterator.hasNext()) {
            result.put(Integer.toString(i++), iterator.next());
        }
        return result;
    }

    @JsonIgnore
    @Nullable
    public String getValue() {
        return getDefaultValue() != null ? (String) getDefaultValue() : null;
    }

    public void setValue(@Nullable String index) {
        setDefaultValue(index);
    }

    @Override
    public ElementSchema<String> value(@Nullable String index) {
        setValue(index);
        return this;
    }


    //
    // Beans
    //

    @JsonProperty("enum")
    public Collection<String> getEnums() {return selectionValues.keySet();}

    public void setSelectionValues(Collection<String> werte) {
        this.selectionValues.clear();
        this.selectionValues.putAll(buildWithIndex(werte));
    }

    public void setSelectionValues(Map<String, String> newValues) {
        this.selectionValues.clear();
        this.selectionValues.putAll(newValues);
    }

    public void addSelectionValue(String id, String value) {
        this.selectionValues.put(id, value);
    }

    @JsonIgnore // not in schema
    public Map<String, String> getSelectionValues() {
        return Collections.unmodifiableMap(this.selectionValues);
    }

    @JsonIgnore // not in schema
    public boolean isViewAsRadios() {return viewAsRadios;}


    public void setViewAsRadios(boolean viewAsRadios) {this.viewAsRadios = viewAsRadios;}

    //
    // builders
    //


    public FComboBox selectionValues(Collection<String> werte) {
        setSelectionValues(werte);
        return this;
    }

    public FComboBox selectionValue(String id, String value) {
        addSelectionValue(id, value);
        return this;
    }

    public FComboBox viewAsRadios(boolean value) {
        setViewAsRadios(value);
        return this;
    }


}
