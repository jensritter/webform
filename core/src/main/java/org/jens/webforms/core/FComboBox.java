package org.jens.webforms.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * https://github.com/jsonform/jsonform/wiki#selection-fields
 *
 * @author Jens Ritter on 29/08/2021.
 */
public class FComboBox extends ElementSchema<String> {
    private final Map<String, String> values = new LinkedHashMap<>();
    private boolean viewAsRadios;

    public FComboBox(String label) {
        super(FormType.FormString, label);
    }

    public FComboBox(String label, Collection<String> values) {
        this(label);
        this.values.putAll(buildWithIndex(values));
    }

    public FComboBox(String label, Map<String, String> values) {
        this(label);
        this.values.putAll(values);
    }

    @JsonProperty("enum")
    public Collection<String> getEnums() {return values.keySet();}


    @Override
    ElementForm buildForm(ElementForm element) {
        element.setTitleMaps(values);
        if(viewAsRadios) {
            element.setType("radios");
        }
        return element;
    }

    @Override
    public ElementSchema<String> value(@Nullable String value) {
        setDefaultValue(Optional.ofNullable(value));
        return this;
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

    public FComboBox addValues(Collection<String> werte) {
        this.values.clear();
        this.values.putAll(buildWithIndex(werte));
        return this;
    }

    public FComboBox addValues(Map<String, String> newValues) {
        this.values.clear();
        this.values.putAll(newValues);
        return this;
    }

    public FComboBox addValue(String id, String value) {
        this.values.put(id, value);
        return this;
    }

    @JsonIgnore
    boolean isViewAsRadios() {return viewAsRadios;}

    public FComboBox setViewAsRadios(boolean viewAsRadios) {
        this.viewAsRadios = viewAsRadios;
        return this;
    }
}
