package org.jens.webforms;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

/**
 * Pojo für WebForm-FORM
 * <p>
 * https://github.com/jsonform/jsonform/wiki#generic-group-the-section-type
 *
 * @author Jens Ritter on 05/09/2021.
 */
@JsonInclude(Include.NON_DEFAULT)
@SuppressWarnings("NegativelyNamedBooleanVariable")
class JsonForm {

    @Nullable private String type;

    @Nullable private String append;

    @Nullable private String htmlClass;

    @Nullable private String fieldHtmlClass;

    private boolean notitle;

    @Nullable private String prepend;

    private boolean disabled;

    private boolean readonly;

    @Nullable private String placeholder;


    public @Nullable String getType() {return type;}

    public void setType(@Nullable String type) {this.type = type;}

    public @Nullable String getAppend() {return append;}

    public void setAppend(@Nullable String append) {this.append = append;}

    public @Nullable String getHtmlClass() {return htmlClass;}

    public void setHtmlClass(@Nullable String htmlClass) {this.htmlClass = htmlClass;}

    public @Nullable String getFieldHtmlClass() {return fieldHtmlClass;}

    public void setFieldHtmlClass(@Nullable String fieldHtmlClass) {this.fieldHtmlClass = fieldHtmlClass;}

    @JsonProperty("notitle")
    public boolean isNoTitle() {return notitle;}

    public void setNoTitle(boolean notitle) {this.notitle = notitle;}

    public @Nullable String getPrepend() {return prepend;}

    public void setPrepend(@Nullable String prepend) {this.prepend = prepend;}

    public boolean isDisabled() {return disabled;}

    public void setDisabled(boolean disabled) {this.disabled = disabled;}

    public boolean isReadonly() {return readonly;}

    public void setReadonly(boolean readonly) {this.readonly = readonly;}

    public @Nullable String getPlaceholder() {return placeholder;}

    public void setPlaceholder(@Nullable String placeholder) {this.placeholder = placeholder;}
}
