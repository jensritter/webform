package org.jens.webforms.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Json-Pojo für JsonForm
 * <p>
 * https://github.com/jsonform/jsonform/wiki#common-field-properties
 * https://github.com/jsonform/jsonform/wiki#a-number-the-range-type
 *
 * @author Jens Ritter on 05/09/2021.
 * @see JsonForm
 */
@JsonInclude(Include.NON_EMPTY)
public class ElementForm extends ElementFormAbstract {

    private final String key;
    @Nullable
    private Boolean notitle; /* true=true, false=null !!!*/
    private String htmlClass;
    private String fieldHtmlClass;
    private String prepend;
    private String placeholder;
    @Nullable
    private Boolean disabled;
    @Nullable
    private Boolean readonly;

    /* for  FComboBox */
    private final Map<String, String> titleMap = new HashMap<>();
    /* for FBoolean */
    private String inlinetitle;

    /* for FRange */
    @Nullable
    private Integer step;
    @Nullable
    private Boolean indicator;


    ElementForm(String key) {this.key = key;}

    void setTitleMaps(Map<String, String> values) {
        this.titleMap.clear();
        this.titleMap.putAll(values);
    }

    void setStep(int value) {this.step = value;}

    void setIndicator(boolean value) {this.indicator = value ? Boolean.TRUE : null;}

    void setInlineTitle(String inlinetitle) {this.inlinetitle = inlinetitle;}

    void setNoTitle(boolean notitle) {
        this.notitle = notitle ? Boolean.TRUE : null;
    }

    void setHtmlClass(String htmlClass) {this.htmlClass = htmlClass;}

    void setFieldHtmlClass(String fieldHtmlClass) {this.fieldHtmlClass = fieldHtmlClass;}

    void setPrepend(String prepend) {this.prepend = prepend;}

    void setPlaceholder(String placeholder) {this.placeholder = placeholder;}

    @SuppressWarnings("NegativelyNamedBooleanVariable")
    void setDisabled(boolean disabled) {this.disabled = disabled ? Boolean.TRUE : null;}

    @JsonIgnore
    boolean isNotTitle() {return notitle != null;}

    @JsonIgnore
    boolean isDisabled() {return disabled != null;}

    void setReadonly(boolean readonly) {this.readonly = readonly ? Boolean.TRUE : null;}

    @JsonIgnore
    boolean isReadonly() {return readonly != null;}


    /** Json-Properties : **/

    public String getKey() {return key;}

    public Map<String, String> getTitleMap() {return Collections.unmodifiableMap(titleMap);}

    public String getInlinetitle() {return inlinetitle;}

    public @Nullable Boolean getNotitle() {return notitle;}

    public String getHtmlClass() {return htmlClass;}

    public String getFieldHtmlClass() {return fieldHtmlClass;}

    public String getPrepend() {return prepend;}

    public String getPlaceholder() {return placeholder;}

    public @Nullable Boolean getDisabled() {return disabled;}

    public @Nullable Boolean getReadonly() {return readonly;}

    public @Nullable Integer getStep() {return step;}

    public @Nullable Boolean getIndicator() {return indicator;}
}
