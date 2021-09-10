package org.jens.webforms;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Pojo für JSON
 *
 * @author Jens Ritter on 10/09/2021.
 */
@SuppressWarnings("NegativelyNamedBooleanVariable")
@JsonInclude(Include.NON_DEFAULT)
public final class JsonSchema {

    private String type;
    private String title;
    private boolean required;
    @Nullable private String description;
    @Nullable private Object defaultValue;
    private int maxLength; //TODO: implement maxLength

    // f. FComboBox
    private final Set<String> enumValues = new HashSet<>();
    // f. FRange
    private int minimum = 0;
    private int maximum = 0;
    private int exclusiveMinimum = 0;
    private int exclusiveMaximum = 0;


    public boolean isRequired() {return required;}

    public void setRequired(boolean required) {this.required = required;}

    public String getType() {return type;}

    public void setType(String type) {this.type = type;}

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public @Nullable String getDescription() {return description;}

    @JsonProperty("default")
    public @Nullable Object getDefaultValue() {return defaultValue;}

    public void setDefaultValue(@Nullable Object defaultValue) {this.defaultValue = defaultValue;}

    public void setDescription(@Nullable String description) {this.description = description;}

    public void setEnum(Set<String> keySet) {
        this.enumValues.clear();
        this.enumValues.addAll(keySet);
    }

    @JsonProperty("enum")
    public Set<String> getEnum() {return Collections.unmodifiableSet(this.enumValues);}

    public int getMaxLength() {return maxLength;}

    public void setMaxLength(int maxLength) {this.maxLength = maxLength;}

    public int getMinimum() {return minimum;}

    public void setMinimum(int minimum) {this.minimum = minimum;}

    public int getMaximum() {return maximum;}

    public void setMaximum(int maximum) {this.maximum = maximum;}

    public int getExclusiveMinimum() {return exclusiveMinimum;}

    public void setExclusiveMinimum(int exclusiveMinimum) {this.exclusiveMinimum = exclusiveMinimum;}

    public int getExclusiveMaximum() {return exclusiveMaximum;}

    public void setExclusiveMaximum(int exclusiveMaximum) {this.exclusiveMaximum = exclusiveMaximum;}
}
