package org.jens.webforms.core;

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
 * https://github.com/jsonform/jsonform/wiki#set-of-fields-the-fieldset-type
 * https://github.com/jsonform/jsonform/wiki#generic-group-the-section-type
 *
 * @author Jens Ritter on 05/09/2021.
 * @see JsonForm
 */
@JsonInclude(Include.NON_DEFAULT)
@SuppressWarnings("NegativelyNamedBooleanVariable")
public class ElementForm extends ElementFormAbstract {
    private final String key;

    private boolean notitle;
    private boolean disabled;
    private boolean readonly;

    @Nullable private String htmlClass;
    @Nullable private String fieldHtmlClass;
    @Nullable private String prepend;
    @Nullable private String append;
    @Nullable private String placeholder;


    /* for  FComboBox */
    private final Map<String, String> titleMap = new HashMap<>();
    /* for FBoolean */
    private @Nullable String inlinetitle;

    /* for FFange */
    @Nullable
    private Integer step;
    private boolean indicator;

    ElementForm(String key) {this.key = key;}

    void setTitleMaps(Map<String, String> values) {
        this.titleMap.clear();
        this.titleMap.putAll(values);
    }

    void setStep(int value) {this.step = value;}

    void setIndicator(boolean value) {this.indicator = value;}

    void setInlineTitle(@Nullable String inlinetitle) {this.inlinetitle = inlinetitle;}

    void setNoTitle(boolean value) {this.notitle = value;}

    void setHtmlClass(@Nullable String htmlClass) {this.htmlClass = htmlClass;}

    void setFieldHtmlClass(@Nullable String fieldHtmlClass) {this.fieldHtmlClass = fieldHtmlClass;}

    void setPrepend(@Nullable String prepend) {this.prepend = prepend;}

    void setAppend(@Nullable String append) {this.append = append;}

    void setPlaceholder(@Nullable String placeholder) {this.placeholder = placeholder;}

    void setDisabled(boolean disabled) {this.disabled = disabled;}

    void setReadonly(boolean readonly) {this.readonly = readonly;}


    /** Json-Properties : **/

    public String getKey() {return key;}

    public Map<String, String> getTitleMap() {return Collections.unmodifiableMap(titleMap);}

    public @Nullable String getInlinetitle() {return inlinetitle;}

    public boolean getNotitle() {return notitle;}

    public @Nullable String getHtmlClass() {return htmlClass;}

    public @Nullable String getFieldHtmlClass() {return fieldHtmlClass;}

    public @Nullable String getPrepend() {return prepend;}

    public @Nullable String getPlaceholder() {return placeholder;}

    public boolean getDisabled() {return disabled;}

    public boolean getReadonly() {return readonly;}

    public @Nullable Integer getStep() {return step;}

    public boolean getIndicator() {return indicator;}

    public @Nullable String getAppend() {return append;}

}
