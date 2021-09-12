package org.jens.webforms;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Json-Pojo für WebForm
 * <p>
 * https://github.com/jsonform/jsonform/wiki#common-field-properties
 * https://github.com/jsonform/jsonform/wiki#set-of-fields-the-fieldset-type
 *
 * @author Jens Ritter on 05/09/2021.
 * @see WebFormBuilder
 */
@JsonInclude(Include.NON_DEFAULT)
public class ElementForm extends JsonForm {
    private final String key;

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

    /** Json-Properties : **/

    public String getKey() {return key;}

    public Map<String, String> getTitleMap() {return Collections.unmodifiableMap(titleMap);}

    public @Nullable String getInlinetitle() {return inlinetitle;}

    public @Nullable Integer getStep() {return step;}

    public boolean getIndicator() {return indicator;}

}
