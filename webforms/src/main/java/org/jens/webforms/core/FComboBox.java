package org.jens.webforms.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * https://github.com/jsonform/jsonform/wiki#selection-fields
 *
 * @author Jens Ritter on 29/08/2021.
 */
public class FComboBox extends ElementSchema<String> {
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
    ElementForm buildForm(ElementForm element) {
        element.setTitleMaps(selectionValues);
        if(viewAsRadios) {
            element.setType("radios");
        }
        return element;
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


    /**
     * Befüllt die Default-Value
     *
     * @param index
     * @return
     */
    @Override
    public ElementSchema<String> value(@Nullable String index) {
        setDefaultValue(index);
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
